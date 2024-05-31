package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * 借鉴JDK9，实现CompletableFuture增强
 * 完成超时时间的设置
 */
@Slf4j
public class CompletableFutureHelper {
    /**
     * 单例延时调度程序，仅用于启动和取消任务
     */
    static final class Delayer {
        static final ScheduledThreadPoolExecutor delayer;
        // 注意，这里使用一个线程就可以搞定 因为这个线程并不真的执行请求 仅仅只是用于启动和取消任务
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new CompletableFutureHelper.Delayer.DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }
        static ScheduledFuture<?> delay(Runnable runnable,
                                        long delay,
                                        TimeUnit unit) {
            return delayer.schedule(runnable, delay, unit);
        }
        static final class DaemonThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }
    }

    /**
     * 超时时以抛异常的形式结束任务
     */
    static final class Timeout implements Runnable {
        final CompletableFuture<?> f;

        Timeout(CompletableFuture<?> f) {
            this.f = f;
        }

        @Override
        public void run() {
            if (f != null && !f.isDone()) {
                //任务未完成，抛异常
                f.completeExceptionally(new TimeoutException());
                //任务未完成，取消任务
                /*try{
                    boolean res = f.cancel(false);
                    if (res) {
                        log.info("Task was successfully cancelled");
                    } else {
                        log.info("Task could not be cancelled");
                    }
                }catch(CompletionException e){

                }*/
            }
        }
    }

    /**
     * 在超时时完成
     */
    static final class DelayedCompleter<U> implements Runnable {
        final CompletableFuture<U> f;
        final U u;

        DelayedCompleter(CompletableFuture<U> f, U u) {
            this.f = f;
            this.u = u;
        }

        @Override
        public void run() {
            if (f != null) {
                f.complete(u);
            }
        }
    }

    /**
     * 取消不需要的超时任务
     */
    static final class Canceller implements BiConsumer<Object, Throwable> {
        final Future<?> f;

        Canceller(Future<?> f) {
            this.f = f;
        }

        @Override
        public void accept(Object ignore, Throwable ex) {
            if (ex == null && f != null && !f.isDone()) {
                f.cancel(false);
            }
        }
    }

    /**
     * 若执行超时返回默认值，否则返回计算出来的结果值
     *
     * @param future  源future
     * @param value   超时返回的默认值
     * @param timeout 超时时间
     * @param unit    超时时间单位
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> completeOnTimeout(CompletableFuture<T> future, T value, long timeout, TimeUnit unit) {
        if (future == null || unit == null) {
            throw new NullPointerException();
        }
        if (null == future.getNow(null)) {
            future.whenComplete(new Canceller(Delayer.delay(new DelayedCompleter<T>(future, value), timeout, unit)));
        }
        return future;
    }

    /**
     * 若执行超时抛出异常，否则返回计算出来的结果值
     *
     * @param future  源future
     * @param timeout 超时时间
     * @param unit    超时时间单位
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> orTimeout(CompletableFuture<T> future, long timeout, TimeUnit unit) {
        if (future == null || unit == null) {
            throw new NullPointerException();
        }
        if (null == future.getNow(null)) {
            future.whenComplete(new Canceller(Delayer.delay(new Timeout(future), timeout, unit)));
        }
        return future;
    }

    /**
     * 包装所有的任务，加上超时处理，超时的任务默认返回null
     *
     * @param timeout 超时时间
     * @param unit    超时时间单位
     * @param cfs     任务
     * @return CompletableFuture<Void>
     * @see CompletableFuture#allOf(CompletableFuture[])
     */
    public static CompletableFuture<Void> allOfWithCompleteOnTimeout(long timeout, TimeUnit unit, CompletableFuture<?>... cfs) {
        Arrays.stream(cfs).forEach(cf -> cf = completeOnTimeout(cf, null, timeout, unit));
        return CompletableFuture.allOf(cfs);
    }
}
