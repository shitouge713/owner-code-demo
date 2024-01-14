package owner.code.demo.thread.threadwait;

import java.util.concurrent.TimeUnit;

public class ThreadOne implements Runnable {
    CommonResource resource ;

    public ThreadOne(CommonResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while(true){
            synchronized(resource){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    while(resource.number!=1){
                        resource.wait();
                    }
                    System.out.println("1");
                    resource.number = 3;
                    resource.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
