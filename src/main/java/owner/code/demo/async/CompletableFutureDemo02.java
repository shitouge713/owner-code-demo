package owner.code.demo.async;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture使用自定义线程池
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
        demo.thenApplyAsyncMethod();
    }

    /**
     * thenApply/thenApplyAsync
     * 第一个任务执行完成后，执行第二个回调方法任务，
     * 会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法有返回值
     */
    public void thenApplyAsyncMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务结果，线程名称：" + Thread.currentThread().getName());
            return "第一个任务结果";
        }, executor);
        CompletableFuture<String> future2 = future1.thenApplyAsync((a) -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第二个任务结果，线程名称：" + Thread.currentThread().getName());
            return "第二个任务结果";
        }, executor);
        System.out.println("主线程执行结束，线程名称：" + Thread.currentThread().getName());
    }

    public void method1() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }, executor);
        future1.thenApply((a) -> {
            //模拟异步任务
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个任务");
            return "第二个任务";
        });
        future1.thenApply((a) -> {
            //模拟异步任务
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第三个任务");
            return "第三个任务";
        });
        future1.thenApply((a) -> {
            //模拟异步任务
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第四个任务");
            return "第四个任务";
        });
        System.out.println(future1);
        //关闭线程池
        executor.shutdown();
    }

}
