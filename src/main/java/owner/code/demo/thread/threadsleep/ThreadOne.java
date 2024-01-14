package owner.code.demo.thread.threadsleep;

import java.util.concurrent.TimeUnit;

public class ThreadOne implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("sleep complete...");
        } catch (InterruptedException e) {
            System.out.println("sleep InterruptedException...");
            //e.printStackTrace();
        }
        System.out.println(123);
    }
}
