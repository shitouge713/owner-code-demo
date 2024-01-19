package owner.code.demo.threadPool;

public class StudyRunner implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + ",学习中");
    }
}
