package owner.code.demo.thread;

/**
 * 线程非安全的i++例子
 */
public class UnsafeStringOperator {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        UnsafeStringOperator operator = new UnsafeStringOperator();
        operator.method();
    }

    public void method() throws InterruptedException {
        final StringBuilder sb = new StringBuilder();
        // 创建并启动所有线程
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    sb.append("a");
                }
            });
            t.start();
        }
        Thread.sleep(3000);
        // 输出最终的计数值
        System.out.println("Actual value: " + 100000);
        System.out.println("Actual value: " + sb.toString().length());
    }
}
