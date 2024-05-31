package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 错误方式：get(5, TimeUnit.SECONDS)阻塞获取
 * CompletableFuture java1.8版本，超时时间处理方式
 * 这种方式超时后，任务并未结束，只是获取结果那里超时了
 */
@Slf4j
public class CompletableFutureDemo05 {
    public static void main(String[] args) throws IOException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟一个耗时操作
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("任务执行结束");
            return "Operation completed successfully";
        });
        try {
            String result = future.get(5, TimeUnit.SECONDS); // 设置5秒超时时间
            log.info(result);
        } catch (InterruptedException | ExecutionException | java.util.concurrent.TimeoutException e) {
            log.info("Operation timed out");
        }
        System.in.read();
    }
}
