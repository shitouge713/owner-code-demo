package owner.code.demo.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>
 * 运单消费者
 * </p>
 *
 * @author dushuai
 * @version V1.0
 * @date 2022-07-20
 */

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.kafka.instantiate", name = "enable", havingValue = "true")
public class WaybillConsumer {

    /**
     * 当一台服务器，concurrency=1时，验证一个消费组下的一个线程是否串行处理多个topic的任务
     * @param record
     * @param ack
     */
    @KafkaListener(topics = "${spring.kafka.orderTopicLocal}", groupId = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "${spring.kafka.topicOrderTest}", groupId = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "${spring.kafka.orderTopicTest}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord record, Acknowledgment ack) {
        log.info("kafka开始接受kafka消息");
        try {
            if (Objects.isNull(record) || Objects.isNull(record.value())) {
                log.error("fatalError,接受到的运单消息内容为空");
                return;
            }
            log.info("kafka消息,record:{}", record);
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("fatalError,WaybillConsumer处理消息异常e:", e);
        } finally {
            ack.acknowledge();
            log.info("kafka监听运单消息完成");
        }
    }
}
