package owner.code.demo.async;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

/**
 * 我们得到一个ID列表，我们希望从中获取一个名称和一个统计信息，并将它们成对地组合起来，所有这些都是异步的。
 * 以下示例使用CompletableFuture类型的列表执行此操作：
 */
public class CompletableFutureDemo02 {
    static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); //定义线程池
    static {
        executor.setCorePoolSize(5); //核心线程数
        executor.setMaxPoolSize(10); //最大线程数
        executor.setQueueCapacity(1000); //等待队列大小
        executor.initialize(); //初始化线程池
    }

    public static void main(String[] args) {
        CompletableFutureDemo02 demo = new CompletableFutureDemo02();
        demo.method();
    }

    public void method() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            //模拟异步任务
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            //模拟异步任务
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        }, executor);
        CompletableFuture<String> result = future1.thenCombine(future2, (str1, str2) -> str1 + " " + str2);
        //当异步任务完成后，获取结果并输出到控制台
        future2.thenAccept((a) -> {
            System.out.println(Thread.currentThread().getName() + ",a");
        });
        System.out.println(Thread.currentThread().getName() + ",程序执行完成");
        //关闭线程池
        executor.shutdown();
    }

}
