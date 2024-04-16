package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import owner.code.demo.kafka.producer.OrderProducer;

@Slf4j
@RestController
@Api(tags = "kafka发送消息控制器", consumes = "application/json")
public class KafkaController {

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping("/sendMsg")
    @ApiOperation("kafka发送消息控制器")
    public String sendMsg(String msg) {
        orderProducer.sendMsg(msg);
        return "发送成功";
    }

    @GetMapping("/sendMsg2")
    @ApiOperation("kafka发送消息控制器2")
    public String sendMsg2(String msg) {
        orderProducer.sendMsg2(msg);
        return "发送成功";
    }

    @GetMapping("/sendMsg3")
    @ApiOperation("kafka发送消息控制器3")
    public String sendMsg3(String msg) {
        orderProducer.sendMsg3(msg);
        return "发送成功";
    }
}
