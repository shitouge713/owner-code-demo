package owner.code.demo.arithmetic.print;

/**
 * 1、循环打印ABC
 * 2、循环打印10以内的奇数偶数
 */
public class PrintABC {
    static boolean t1Running = true;
    static boolean t2Running = false;
    static boolean t3Running = false;

    public synchronized void printA () {
        while(!t1Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } }
        System.out.print(Thread.currentThread().getName());
        t1Running = false;
        t2Running = true;
        t3Running = false;
        this.notifyAll();
    }

    public synchronized void printB () {
        while(!t2Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        t1Running = false;
        t2Running = false;
        t3Running = true;
        this.notifyAll();
    }

    public synchronized void printC () {
        while(!t3Running) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        t1Running = true;
        t2Running = false;
        t3Running = false;
        this.notifyAll();
    }
}
