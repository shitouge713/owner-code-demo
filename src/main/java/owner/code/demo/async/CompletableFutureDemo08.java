package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 演示不推荐的写法
 * 推荐超时写法见【CompletableFutureTimeoutUtil】
 */
@Slf4j
public class CompletableFutureDemo08 {
    private static final Integer reqTimeOut = 3;

    public static void main(String[] args) {
        log.info("任务开始");
        // 首页金刚位
        CompletableFuture<String> banner = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第一个任务结果:首页金刚位 - 执行结束");
            return "第一个任务结果:首页金刚位";
        });
        // 附近货柜
        CompletableFuture<String> lbs = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第二个任务结果:附近货柜 - 执行结束");
            return "第二个任务结果:附近货柜";
        });
        // 扭蛋抽奖
        CompletableFuture<String> lottery = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第三个任务结果:扭蛋抽奖 - 执行结束");
            return "第三个任务结果:扭蛋抽奖";
        });
        // 签到
        CompletableFuture<String> signStatus = CompletableFuture.supplyAsync(() -> {
            sleep(5L);
            log.info("第四个任务结果:签到 - 执行结束");
            return "第四个任务结果:签到";
        });
        /**
         * 这种写法有问题，不推荐这种写法
         * 1. banner.get(reqTimeOut, TimeUnit.SECONDS)执行时是阻塞的，也就是后面的get不会执行
         * 下面四个获取的逻辑，如果每次get都耗时3s，整个过程就是耗时12s
         * 2. 即便超时获取不到，也阻止不了任务的执行
         *
         */
        try {
            log.info(banner.get(reqTimeOut, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("fatalError,首页金刚位异常", e);
        }
        try {
            log.info(lbs.get(reqTimeOut, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("fatalError,首页附近货柜异常", e);
        }
        try {
            log.info(lottery.get(reqTimeOut, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("fatalError,首页扭蛋抽奖异常", e);
        }
        try {
            log.info(signStatus.get(reqTimeOut, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("fatalError,首页签到异常", e);
        }
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
