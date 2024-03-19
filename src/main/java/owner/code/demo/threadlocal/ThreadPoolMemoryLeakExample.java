package owner.code.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolMemoryLeakExample {
    // 定义一个静态的ThreadLocal变量，并存储大对象
    private static final ThreadLocal<byte[]> threadLocalLargeObject = new ThreadLocal<>();

    public static void main(String[] args) {
        // 创建一个单线程的线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交任务到线程池，任务中向ThreadLocal设置大对象
        executor.submit(() -> {
            // 向ThreadLocal设置一个大对象（假设10MB）
            threadLocalLargeObject.set(new byte[10 * 1024 * 1024]);

            // 注意：这里没有调用threadLocalLargeObject.remove();
            // 在实际应用中，应该在适当的时候调用remove方法来防止内存泄露
        });

        // 关闭线程池
        executor.shutdown();

        // 模拟一段时间后的操作
        try {
            Thread.sleep(5000); // 等待一段时间，让任务执行完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 尝试促进垃圾回收
        System.gc();
        System.out.println("垃圾回收已尝试执行，如果ThreadLocal未被清理，大对象仍然被引用，可能导致内存泄露");
    }
}
