package com.adam.producerservice.controller;

import com.adam.producerservice.QueueProducer;
import com.adam.producerservice.dto.NotificationRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adambchouti on 2019-04-02.
 * @project producer-service
 */
@RestController
public class ExampleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final QueueProducer queueProducer;

    public ExampleController(QueueProducer queueProducer) {
        this.queueProducer = queueProducer;
    }

    @GetMapping("/produce")
    public void produce() throws JsonProcessingException {
        logger.info("Called produce endpoint...");
        queueProducer.produce(new NotificationRequestDTO("Hello World"));
    }
}
