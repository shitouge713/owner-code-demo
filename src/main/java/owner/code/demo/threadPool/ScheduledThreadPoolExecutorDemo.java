package owner.code.demo.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor示例
 */
@Slf4j
public class ScheduledThreadPoolExecutorDemo {
    static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("com.alibaba.nacos.client.config.security.updater-%d");
            return t;
        }
    });

    public static void main(String[] args) {
        ScheduledThreadPoolExecutorDemo.delayAfterTask();
    }

    /**
     * 固定频率任务，4s产生一个任务，如果任务来不及消费，会存储在对应的队列中
     * 任务来不及消费的话，会造成周期不准确，因为要等到有空闲线程的时候再去执行
     */
    public static void cycleTask() {
        executorService.scheduleAtFixedRate(() -> {
            // 每
            log.info(Thread.currentThread().getName() + ",在线程启动的1s后执行，且随后周期执行的时间为1s");
            try {
                // 线程执行时间为3s：3<2，所以任务执行完了马上进行下一个任务
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 延迟任务
     */
    public static void delay() {
        executorService.schedule(() -> {
            log.info(Thread.currentThread().getName() + ",在线程启动的1s后执行，只执行一次");
            try {
                // 线程执行时间为3s：3<2，所以任务执行完了马上进行下一个任务
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * 固定延迟任务
     * 周期任务，和1的区别：
     * 在任务结束后多久执行
     */
    public static void delayAfterTask() {
        executorService.scheduleWithFixedDelay(() -> {
            log.info(Thread.currentThread().getName() + ",在线程启动的1s后执行，只执行一次");
            try {
                // 线程执行时间为3s：所以任务执行完了马上进行下一个任务
                int args = new Random().nextInt(10);
                TimeUnit.SECONDS.sleep(args);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 4, TimeUnit.SECONDS);
    }
}
