package owner.code.demo.threadPool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("recv-pool-%d").build();
        ExecutorService recvThreadPool = new ThreadPoolExecutor(4, 6,60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(20), namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 3; i++) {
            recvThreadPool.execute(new StudyRunner());
        }
    }
}
