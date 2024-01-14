package owner.code.demo.arithmetic.print;

/**
 * 1、循环打印AB
 * 2、循环打印10以内的奇数偶数
 */
public class PrintAB {
    static boolean t1Running = true;
    static boolean t2Running = false;
    private Integer num = 10;
    public synchronized void printA () {
        while(!t1Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } }
        System.out.print(Thread.currentThread().getName()+":"+num-- +"\n");
        t1Running = false;
        t2Running = true;
        this.notify();
    }
    public synchronized void printB () {
        while(!t2Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName()+":"+num-- +"\n");
        t1Running = true;
        t2Running = false;
        this.notify();
    }
}
