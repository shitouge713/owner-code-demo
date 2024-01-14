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
    @KafkaListener(topics = "${spring.kafka.testTopic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord record, Acknowledgment ack) {
        log.info("kafka开始接受kafka消息");
        try {
            if (Objects.isNull(record) || Objects.isNull(record.value())) {
                log.error("fatalError,接受到的运单消息内容为空");
                return;
            }
            log.info("kafka消息,record:{}", record.toString());
        } catch (Exception e) {
            log.error("fatalError,WaybillConsumer处理消息异常e:", e);
        } finally {
            ack.acknowledge();
            log.info("kafka监听运单消息完成");
        }
    }
}
