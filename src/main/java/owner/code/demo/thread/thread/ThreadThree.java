package owner.code.demo.thread.thread;

import java.util.concurrent.TimeUnit;

public class ThreadThree implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(6000);
            System.out.println("three");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
