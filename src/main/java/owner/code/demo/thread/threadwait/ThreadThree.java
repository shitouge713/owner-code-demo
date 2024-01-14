package owner.code.demo.thread.threadwait;

import java.util.concurrent.TimeUnit;

public class ThreadThree implements Runnable{
    CommonResource resource ;

    public ThreadThree(CommonResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while(true){
            synchronized (resource){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    while(resource.number!=3){
                        resource.wait();
                    }
                    System.out.println("3");
                    resource.number = 2;
                    resource.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
