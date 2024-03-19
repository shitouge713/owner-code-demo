package owner.code.demo.thread;

/**
 * 局部变量，引用了外部对象，这个对象是一个共享资源，这个局部变量也是非线程安全的
 *
 */
public class ThreadNotSafeDemo {
    private static class Counter {
        private int count = 0;
        public void increment() {
            count++; // 这一操作不是原子性的，实际上包含了读取当前值、增加1、写回新值三个步骤
        }
        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadNotSafeDemo demo = new ThreadNotSafeDemo();
        demo.method();
    }

    public  void method() throws InterruptedException {
        final Counter counter = new Counter();
        // 创建100个线程，每个线程对同一个计数器增加10000次
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        // 等待所有线程执行完毕
        for (Thread t : threads) {
            t.join();
        }
        // 输出最终的计数值
        System.out.println("Final count: " + counter.getCount()); // 预期是1000000，但实际上可能会小于这个值
    }

}
