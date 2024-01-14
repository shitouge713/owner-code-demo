package owner.code.demo.dieThread;

/**
 * 写一个死锁的例子
 */
public class DieThread {

    public static void main(String[] args) {
        DieThread thread = new DieThread();
        thread.method();
    }

    public void method(){
        final Object obj1 = new Object();
        final Object obj2 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1){
                    try {
                        System.out.println(Thread.currentThread().getName()+"获取obj1锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj2){
                        System.out.println(Thread.currentThread().getName() + " got obj2");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj2){
                    try {
                        System.out.println(Thread.currentThread().getName()+"获取obj2锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj1){
                        System.out.println(Thread.currentThread().getName() + " got obj1");
                    }
                }
            }
        }).start();
    }
}
