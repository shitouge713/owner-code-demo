package owner.code.demo.async;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * 重点
 * CompletableFuture java1.8版本，超时时间处理方式
 */
@Slf4j
public class CompletableFutureTimeoutUtil {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, TimeoutException {
        // 首页金刚位
        CompletableFuture<String> banner = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第一个任务结果:首页金刚位 - 执行结束");
            return "第一个任务结果:首页金刚位";
        });
        // 附近货柜
        CompletableFuture<String> lbs = CompletableFuture.supplyAsync(() -> {
            sleep(11L);
            log.info("第二个任务结果:附近货柜 - 执行结束");
            return "第二个任务结果:附近货柜";
        });
        // 扭蛋抽奖
        CompletableFuture<String> lottery = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第三个任务结果:扭蛋抽奖 - 执行结束");
            return "第三个任务结果:扭蛋抽奖";
        });
        // 签到
        CompletableFuture<String> signStatus = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第四个任务结果:签到 - 执行结束");
            return "第四个任务结果:签到";
        });
        /*CompletableFuture<String> a1 = CompletableFutureTimeoutUtil.completeOnTimeout("任务1超时执行", banner, 6, TimeUnit.SECONDS);
        CompletableFuture<String> a2 = CompletableFutureTimeoutUtil.completeOnTimeout("任务2超时执行", lbs, 6, TimeUnit.SECONDS);
        CompletableFuture<String> a3 = CompletableFutureTimeoutUtil.completeOnTimeout("任务3超时执行", lottery, 6, TimeUnit.SECONDS);
        CompletableFuture<String> a4 = CompletableFutureTimeoutUtil.completeOnTimeout("任务4超时执行", signStatus, 6, TimeUnit.SECONDS);
        *//**
         * 推荐方式：获取结果/将结果设置为属性，通过下面的这种方式异步操作
         *//*
        a1.thenAcceptAsync(a -> {
            //此处可以替换为将a设置到对象属性中
            log.info("任务1执行结果：" + a);
        });
        a2.thenAcceptAsync(a -> {
            //此处可以替换为将a设置到对象属性中
            log.info("任务2执行结果：" + a);
        });
        a3.thenAcceptAsync(a -> {
            //此处可以替换为将a设置到对象属性中
            log.info("任务3执行结果：" + a);
        });
        a4.thenAcceptAsync(a -> {
            //此处可以替换为将a设置到对象属性中
            log.info("任务4执行结果：" + a);
        });*/
        //get()、join()慎用
        log.info("任务1执行结果：" + banner.get(6000L, TimeUnit.MILLISECONDS));
        log.info("任务2执行结果：" + lbs.get(6000L, TimeUnit.MILLISECONDS));
        log.info("任务3执行结果：" + lottery.get(6000L, TimeUnit.MILLISECONDS));
        log.info("任务4执行结果：" + signStatus.get(6000L, TimeUnit.MILLISECONDS));
        System.in.read();
    }

    /**
     * Singleton delay scheduler, used only for starting and * cancelling tasks.
     */
    static final class Delayer {
        static final ScheduledThreadPoolExecutor delayer;

        // 注意，这里使用一个线程就可以搞定 因为这个线程并不真的执行请求 而是仅仅抛出一个异常
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new CompletableFutureTimeoutUtil.Delayer.DemoThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }

        static ScheduledFuture<?> delay(Runnable command, long delay,
                                        TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DemoThreadFactory implements ThreadFactory {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        // 延时队列，等待多久后执行某个操作，这里的表现
        CompletableFutureTimeoutUtil.Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    /**
     * 哪个先完成 就apply哪一个结果 这是一个关键的API,exceptionally出现异常后返回默认值
     * 用于指定两个 CompletableFuture 对象中任意一个完成后，对其结果应用指定的函数。
     * 该方法返回一个新的 CompletableFuture 对象，其结果是应用函数后的结果。
     * @param t
     * @param future
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> completeOnTimeout(T t, CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return future.applyToEither(timeoutFuture, Function.identity()).exceptionally((throwable) -> t);
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
