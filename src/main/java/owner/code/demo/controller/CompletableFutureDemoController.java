package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@Api(tags = "CompletableFutureDemo", consumes = "application/json")
public class CompletableFutureDemoController {

    @GetMapping("/completableFutureTest1")
    @ApiOperation("测试调用线程结束，CompletableFuture异步任务是否会结束")
    public void completableFutureTest1() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            //模拟异步任务
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "futureResult";
        });
        String join = future1.join();
        System.out.println("异步任务的执行结果:" + join + ",去处理其他逻辑");
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程逻辑");
        }
    }

    @GetMapping("/completableFutureTest2")
    @ApiOperation("测试调用线程结束，CompletableFuture异步任务是否会结束")
    public void completableFutureTest2() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "futureResult";
        });
        future1.handle((a, throwable) -> {
            //异步任务的执行结果去处理其他逻辑
            this.method(a);
            return null;
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程逻辑");
        }
    }

    public void method(String a) {
        System.out.println("异步任务的执行结果:" + a + ",去处理其他逻辑");
    }
}
