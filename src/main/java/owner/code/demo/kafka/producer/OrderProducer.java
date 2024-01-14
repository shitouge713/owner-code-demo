package owner.code.demo.kafka.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 测试生产者
 * </p>
 *
 * @author dushuai
 * @version V1.0
 * @date 2022-07-20
 */
@Component
@Slf4j
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${spring.kafka.testTopic}")
    private String testTopic;

    /**
     * 发送消息
     *
     * @return
     */
    public void sendMsg(String message) {
        log.info("order-kafka-send,messageDTO:{}", JSON.toJSONString(message));
        try {
            ListenableFuture<SendResult<String, String>> future = template.send(testTopic, JSON.toJSONString(message));
            CompletableFuture<SendResult<String, String>> completable = future.completable();
            completable.whenCompleteAsync((n, e) -> {
                if (null != e) {
                    System.out.println("发送报错了");
                } else {
                    System.out.println("发送成功了！");
                }
            });
        } catch (Exception e) {
            log.error("fatalError,订单消息异常,messageDTO:{},e:", JSON.toJSONString(message), e);
        }
        log.info("kafka发送消息完成");
    }

    public void sendMsg2(String message) {
        ListenableFuture<SendResult<String, String>> future = template.send(testTopic, JSON.toJSONString(message));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送报错了");
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("发送成功了！");
            }
        });
    }
}