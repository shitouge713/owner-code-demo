package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture java1.8版本，超时时间处理方式
 */
@Slf4j
public class CompletableFutureDemo06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        long start = System.currentTimeMillis();
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            sleep(3L);
            System.out.printf("future1 [%s] end\n", Thread.currentThread().getName());
            return "[data] future1";
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            sleep(8L);
            System.out.printf("future2 [%s] end\n", Thread.currentThread().getName());
            return "[data] future2";
        });
        //CompletableFuture.allOf(future1, future2).join();
        log.info("main get start...");
        /*场景1：包装任务，加上超时处理，超时的任务默认返回null
        future1 = CompletableFutureHelper.completeOnTimeout(future1, null, 4, TimeUnit.SECONDS);
        future2 = CompletableFutureHelper.completeOnTimeout(future2, null, 4, TimeUnit.SECONDS);
        CompletableFuture.allOf(future1, future2).join();
        场景2：包装任务，加上超时处理，任一任务超时则抛错TimeoutException
        future1 = CompletableFutureHelper.orTimeout(future1, 4, TimeUnit.SECONDS);
        future2 = CompletableFutureHelper.orTimeout(future2, 4, TimeUnit.SECONDS);
        CompletableFuture.allOf(future1, future2).join();
        此一行代码等价于场景一3行代码，使用更方便；不过场景一中可以分别指定不同的默认返回值，更加灵活*/
        CompletableFutureHelper.allOfWithCompleteOnTimeout(4, TimeUnit.SECONDS, future1, future2).join();
        /*超时时间设置成7995毫秒，执行时间为8s的线程不一定会超时（本次测试精度大概在10ms左右，7990时，执行8s的任务大概率当做超时处理）
        CompletableFutureHelper.allOfWithCompleteOnTimeout(7995, TimeUnit.MILLISECONDS, future1, future2).join();*/
        log.info("[get future1] " + future1.get());
        log.info("[get future2] " + future2.get());
        log.info("main get end...");
        System.out.printf("main [%s] end cost %d ms\n", Thread.currentThread().getName(), System.currentTimeMillis() - start);
        //阻塞main线程，防止main线程结束
        System.in.read();
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
