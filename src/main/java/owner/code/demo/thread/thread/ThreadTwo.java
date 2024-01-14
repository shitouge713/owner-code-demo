package owner.code.demo.thread.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTwo  implements Runnable {
    Thread t;

    public ThreadTwo(Thread t) {
        this.t = t;
    }
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
            t.join();
            System.out.println("two");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
