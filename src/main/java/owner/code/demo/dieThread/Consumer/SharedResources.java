package owner.code.demo.dieThread.Consumer;

public class SharedResources {
    /**
     * 要被锁的对象
     */
    public Object object = new Object();
    /**
     * 共享资源
     */
    public Integer number = 0;

    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        Consumer consumer = new Consumer(sharedResources);
        Producer producer = new Producer(sharedResources);
        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);
        t1.start();
        t2.start();
    }
}
