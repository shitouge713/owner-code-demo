package owner.code.demo.thread.threadwait;

import java.util.concurrent.TimeUnit;

public class ThreadTwo  implements Runnable {
    CommonResource resource ;

    public ThreadTwo(CommonResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while(true){
            synchronized (resource){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    while(resource.number!=2){
                        resource.wait();
                    }
                    System.out.println("2");
                    resource.number = 1;
                    resource.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
