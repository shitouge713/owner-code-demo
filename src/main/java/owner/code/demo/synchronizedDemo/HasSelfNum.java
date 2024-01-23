package owner.code.demo.synchronizedDemo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
public class HasSelfNum implements Runnable {
    private int num = 0;

    @Override
    public void run() {
        add();
    }

    public synchronized void add() {
        for (int i = 0; i < 10; i++) {
            num++;
            log.info("当前线程:{},num = {}", Thread.currentThread().getName(), num);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
