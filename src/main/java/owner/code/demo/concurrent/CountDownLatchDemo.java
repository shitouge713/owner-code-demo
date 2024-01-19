package owner.code.demo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        //创建多线程
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch downLatch = new CountDownLatch(3);
        Worker w1 = new Worker(downLatch, "张三");
        Worker w2 = new Worker(downLatch, "李四");
        Worker w3 = new Worker(downLatch, "王五");
        BossWork boss = new BossWork(downLatch);
        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(boss);
        executor.shutdown();
    }
}
