package owner.code.demo.condition;

import java.util.concurrent.TimeUnit;

public class ConditionProduce implements Runnable {

    SharedResources sharedResources;

    public ConditionProduce(SharedResources sharedResources) {
        this.sharedResources = sharedResources;
    }

    @Override
    public void run() {
        while (true) {
            sharedResources.lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (sharedResources.integer >= 5) {
                try {
                    System.out.println("00---资源已满，需要等待...");
                    sharedResources.produce.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("00---有线程消费，可以生产...");
            sharedResources.integer++;
            sharedResources.consumer.signalAll();
            sharedResources.lock.unlock();
        }
    }
}
