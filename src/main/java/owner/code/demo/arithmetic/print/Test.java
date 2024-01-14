package owner.code.demo.arithmetic.print;

public class Test {
    public static void main(String[] args) {
        methodAB();
    }
    public static void methodAB(){
        final PrintAB print = new PrintAB();
        new Thread(new Runnable() {
            public void run(){
                for(int i=0;i<5;i++) {
                    print.printA();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<5;i++) {
                    print.printB();
                }
            }
        }).start();
    }
    public static void methodABC(){
        final PrintABC print = new PrintABC();
        new Thread(new Runnable() {
            public void run(){
                for(int i=0;i<5;i++) {
                    print.printA();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<5;i++) {
                    print.printB();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<5;i++) {
                    print.printC();
                }
            }
        },"C").start();
    }
}
