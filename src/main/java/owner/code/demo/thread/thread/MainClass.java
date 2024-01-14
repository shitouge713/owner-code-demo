package owner.code.demo.thread.thread;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {

        ThreadThree three = new ThreadThree();
        Thread t3 = new Thread(three);

        ThreadTwo two = new ThreadTwo(t3);
        Thread t2 = new Thread(two);

        ThreadOne one = new ThreadOne(t2);
        Thread t1 = new Thread(one);

        t1.start();
        t2.start();
        t3.start();
        System.out.println("main");
    }
}
