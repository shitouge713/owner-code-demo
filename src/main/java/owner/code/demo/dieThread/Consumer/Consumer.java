package owner.code.demo.dieThread.Consumer;

public class Consumer implements Runnable {
    SharedResources data;
    public Consumer(SharedResources data){
        this.data = data;
    }
    @Override
    public void run() {
        while(true){
            synchronized (data){
                try {
                    if(data.number==0){
                        data.wait();
                    }
                    data.number--;
                    System.out.println("Consumer:"+data.number);
                    data.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
