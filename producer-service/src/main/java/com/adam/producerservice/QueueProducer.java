package com.adam.producerservice;

import com.adam.producerservice.dto.NotificationRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author adambchouti on 2019-04-02.
 * @project producer-service
 */
@Component
public class QueueProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${fanout.exchange}")
    private String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(NotificationRequestDTO notificationDTO) throws JsonProcessingException {
        logger.info("Storing notification...");
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(notificationDTO));
        logger.info("Notification stored in queue successfully");
    }
}
