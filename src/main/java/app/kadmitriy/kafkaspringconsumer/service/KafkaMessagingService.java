package app.kadmitriy.kafkaspringconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.kadmitriy.kafkaspringconsumer.model.OrderEvent;

@Service
public class KafkaMessagingService {
	
	private final Logger log = LoggerFactory.getLogger(KafkaMessagingService.class);
	
    private static final String topicCreateOrder = "${topic.send-order}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";


    @Transactional
    @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=app.kadmitriy.kafkaspringconsumer.model.OrderEvent"})
    public OrderEvent printOrder(OrderEvent orderEvent) {
        log.info("The product: {} was ordered in quantity: {} and at a price: {}", orderEvent.getProductName(), orderEvent.getQuantity(), orderEvent.getPrice());
        log.info("To pay: {}", orderEvent.getPrice() );
        return orderEvent;
    }
}
