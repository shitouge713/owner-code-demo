package owner.code.demo.dieThread;

import org.springframework.stereotype.Component;

/**
 * 模拟synchronized死锁
 * 可以观察kill掉一个线程后，锁是否释放
 */
@Component
public class DeadlockExample {
    // 创建两个资源
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();
    // 线程1尝试先锁定resource1，然后尝试锁定resource2
    Thread thread1 = new Thread(() -> {
        synchronized (resource1) {
            System.out.println("Thread 1: Locked resource 1");
            try {
                // 模拟操作资源，增加发生死锁的可能性
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1: Waiting for resource 2");
            synchronized (resource2) {
                System.out.println("Thread 1: Locked resource 2");
            }
        }
    });

    // 线程2尝试先锁定resource2，然后尝试锁定resource1
    Thread thread2 = new Thread(() -> {
        synchronized (resource2) {
            System.out.println("Thread 2: Locked resource 2");
            try {
                // 模拟操作资源，增加发生死锁的可能性
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2: Waiting for resource 1");
            synchronized (resource1) {
                System.out.println("Thread 2: Locked resource 1");
            }
        }
    });

    public void startThreads() {
        thread1.setName("deadThread-1");
        thread2.setName("deadThread-2");
        thread1.start();
        thread2.start();
    }
}
