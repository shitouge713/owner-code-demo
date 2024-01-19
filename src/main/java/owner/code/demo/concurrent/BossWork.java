package owner.code.demo.concurrent;

import java.util.concurrent.CountDownLatch;

public class BossWork implements Runnable {

    private CountDownLatch downLatch;

    public BossWork(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    public void run() {
        System.out.println("老板等待所有的工人干完活......");
        try {
            this.downLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("工人活都干完了，老板开始检查了！");
    }
}
