package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * 我们得到一个ID列表，我们希望从中获取一个名称和一个统计信息，并将它们成对地组合起来，所有这些都是异步的。
 * 以下示例使用CompletableFuture类型的列表执行此操作：
 */
@Slf4j
public class CompletableFutureDemo01 {

    public static void main(String[] args) {
        CompletableFutureDemo01 demo = new CompletableFutureDemo01();
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
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            //模拟异步任务
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, (str1, str2) -> str1 + " " + str2);
        //当异步任务完成后，获取结果并输出到控制台
        /**
         * 如果没有result.join()这句话
         * 异步线程阻塞了3s，主线程执行完，异步线程挂了？为什么
         * 所以后面这行日志不会打印，log.info(Thread.currentThread().getName() + ",a");
         * 两种方式可以避免：
         * 1、使用result.join()或者result.get()
         * 2、使用自定义线程池，示例CompletableFutureDemo02
         */
        result.thenAccept((a) -> {
            System.out.println(Thread.currentThread().isDaemon());
            log.info(Thread.currentThread().getName() + ",a");
        });
        //等待异步任务完成并获取结果
        result.join();
        System.out.println(Thread.currentThread().isDaemon());
        log.info(Thread.currentThread().getName() + ",程序执行完成");
    }

}
