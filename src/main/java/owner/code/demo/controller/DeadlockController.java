package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import owner.code.demo.dieThread.DeadlockExample;
import owner.code.demo.service.AsyncDemoServiceImpl;

@Slf4j
@RestController
public class DeadlockController {

    @Autowired
    private DeadlockExample deadlockExample;


    @GetMapping("/deadlockExample")
    @ApiOperation("测试异步调用相关逻辑2")
    public void deadlockExample() {
        System.out.println("deadlockExample：" + Thread.currentThread().getName());
        //deadlockExample.startThreads();
    }

}
