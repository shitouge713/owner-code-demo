package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import owner.code.demo.service.TransactionalServiceAImpl;

@Slf4j
@RestController
@Api(tags = "AsyncDemo", consumes = "application/json")
public class TransactionalController {

    @Autowired
    private TransactionalServiceAImpl transactionalServiceAImpl;

    @GetMapping("/transaction/asyncMethod")
    @ApiOperation("测试异步调用相关逻辑1")
    public void asyncMethod() throws Exception {
        System.out.println("controller.asyncMethod，线程名称：" + Thread.currentThread().getName());
        transactionalServiceAImpl.asyncMethod3();
    }

    @GetMapping("/transaction/reduceKu")
    @ApiOperation("测试高并发下库存扣减")
    public String reduceKu()  {
        return transactionalServiceAImpl.reduceKu();
    }



}
