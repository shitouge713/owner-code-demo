package owner.code.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试AtomicInteger线程安全性
 */
public class AtomicMain {

    public static void main(String[] args) throws Exception {
        AtomicMain main = new AtomicMain();
        main.method1();
        main.method2();
    }
    public void method1() throws InterruptedException {
        AtomicInteger a = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                a.getAndIncrement();
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(a.get());
    }
    public void method2() throws InterruptedException {
        Counter c = new Counter();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                c.addCount();
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(c.getCount());
    }
}
