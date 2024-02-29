package owner.code.demo.queue;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 比较存储相同元素
 * 1、array和linked占用内存大小
 * 2、array和linked执行效率
 */
public class ArrayBlockingQueueDemo {

    private static final Integer UNIT_B = 1024;

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<byte[]> array = new ArrayBlockingQueue(10000);
        LinkedBlockingQueue<byte[]> link = new LinkedBlockingQueue(10000);
        for (int i = 0; i < 10000; i++) {
            array.add(new byte[1 * UNIT_B]);
            link.add(new byte[1 * UNIT_B]);
        }
        System.out.println(array);
        System.out.println(link);
        Thread.sleep(200000);
    }
}
