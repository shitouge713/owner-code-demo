package owner.code.demo.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private CountDownLatch downLatch;
    private String name;

    public Worker(CountDownLatch downLatch, String name) {
        this.downLatch = downLatch;
        this.name = name;
    }

    public void run() {
        this.doWork();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException ie) {
        }
        System.out.println(this.name + "-结束-工作！");
        this.downLatch.countDown();

    }

    private void doWork() {
        System.out.println(this.name + "-开始-干活!");
    }
}
