package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture java1.8版本，超时时间处理方式
 */
@Slf4j
public class CompletableFutureDemo07 {
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(
                    1,
                    r -> {
                        Thread thread = new Thread(r);
                        thread.setName("failAfter-%d");
                        thread.setDaemon(true);
                        return thread;
                    });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("-------开始------");
        final CompletableFuture<Object> oneSecondTimeout = failAfter(2)
                .exceptionally(xxx -> "超时");
        List<Object> collect = Stream.of("1", "2", "3", "4", "5", "6", "7")
                .map(x -> CompletableFuture.anyOf(createTaskSupplier(x)
                        , oneSecondTimeout))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println("-------结束------");
        System.out.println(collect);
    }

    public static CompletableFuture<String> createTaskSupplier(String x) {
        return CompletableFuture.supplyAsync(getStringSupplier(x))
                .exceptionally(xx -> xx.getMessage());
    }

    public static Supplier<String> getStringSupplier(String text) {
        return () -> {
            System.out.println("开始 " + text);
            if ("1".equals(text)) {
                throw new RuntimeException("运行时错误");
            }
            try {
                if ("5".equals(text))
                    setSleepTime(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("停止 " + text);
            return text + "号";
        };
    }

    static void setSleepTime(int SleepTime) throws InterruptedException {
        TimeUnit.SECONDS.sleep(SleepTime);
    }

    public static <T> CompletableFuture<T> failAfter(long timeout) {
        final CompletableFuture<T> task = new CompletableFuture<>();
        scheduler.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + timeout);
            return task.completeExceptionally(ex);
        }, timeout, TimeUnit.MILLISECONDS);
        return task;
    }
}
