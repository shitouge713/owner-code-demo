package owner.code.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Slf4j
public class ThreadPoolConfig {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    @Bean("myselfExecutor")
    public Executor myselfExecutor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(null, r, "myThread-" + poolNumber.getAndIncrement(), 0);
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                20,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                threadFactory);
        return executor;
    }
}
