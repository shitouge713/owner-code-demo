package owner.code.demo.dieThread.Consumer;


/**
 * 线程间通信
 * 方式1、Object的wait，notify方法
 *思路：
 * wait和notify需要在synchronized方法中调用，而且synchronized需要锁同一个对象
 * 一个线程启动消费者，如果共享资源为空，本线程wait，否则消费，消费后notify通知其他线程
 * 一个线程启动生产者，如果共享资源最大，本线程wait，否则生产，生产后notifyAll通知其他线程
 */
public class Producer implements Runnable {
    SharedResources data;
    public Producer(SharedResources data){
        this.data = data;
    }
    @Override
    public void run() {
        while(true){
            synchronized (data){
                try {
                    if(data.number==5){
                        data.wait();
                    }
                    data.number++;
                    System.out.println("Producer:"+data.number);
                    data.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

