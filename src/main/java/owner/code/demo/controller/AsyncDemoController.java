package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import owner.code.demo.service.AsyncDemoServiceImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@RestController
@Api(tags = "AsyncDemo", consumes = "application/json")
public class AsyncDemoController {

    @Autowired
    private AsyncDemoServiceImpl asyncDemoService;


    @GetMapping("/asyncMethod2")
    @ApiOperation("测试异步调用相关逻辑2")
    public void asyncMethod2() {
        System.out.println("controller.asyncMethod2，线程名称：" + Thread.currentThread().getName());
        asyncDemoService.asyncMethod2();
    }

}
