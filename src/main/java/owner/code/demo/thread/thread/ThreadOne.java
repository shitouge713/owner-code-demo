package owner.code.demo.thread.thread;

import java.util.concurrent.TimeUnit;

public class ThreadOne implements Runnable {
    Thread t;

    public ThreadOne(Thread t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            t.join();
            System.out.println("one");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
