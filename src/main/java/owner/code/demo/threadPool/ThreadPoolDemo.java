package owner.code.demo.threadPool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("recv-pool-%d").build();
        ExecutorService recvThreadPool = new ThreadPoolExecutor(4, 6,60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(20), namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        recvThreadPool.execute(new StudyRunner());
        Thread.sleep(500000);
        recvThreadPool.execute(new StudyRunner());
    }
}
