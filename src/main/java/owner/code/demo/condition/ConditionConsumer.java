package owner.code.demo.condition;

import java.util.concurrent.TimeUnit;

public class ConditionConsumer  implements  Runnable{
    SharedResources sharedResources;
    public ConditionConsumer(SharedResources sharedResources){
        this.sharedResources = sharedResources;
    }
    @Override
    public void run(){
        while(true){
            sharedResources.lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(sharedResources.integer<=0){
                try {
                    System.out.println("00---没有资源，需要等待...");
                    sharedResources.consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sharedResources.integer--;
            System.out.println("00---获取资源，执行消费..."+sharedResources.integer);
            sharedResources.produce.signalAll();
            sharedResources.lock.unlock();
        }
    }
}