package owner.code.demo.thread;

/**
 * 线程非安全的i++例子
 */
public class UnsafeCounter {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        final int numberOfThreads = 100; // 线程数量
        Thread[] threads = new Thread[numberOfThreads];
        // 创建并启动所有线程
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++; // 非原子操作
                }
            });
            threads[i].start();
        }
        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }
        // 输出最终的计数值
        System.out.println("Expected value: " + (numberOfThreads * 1000));
        System.out.println("Actual value: " + count);
    }
}
