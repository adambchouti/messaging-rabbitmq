package com.adam.consumerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author adambchouti on 2019-04-02.
 * @project consumer-service
 */
@Component
public class QueueConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message) {
        logger.info("Received (String) " + message);
        processMessage(message);
    }
    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String strMessage) {
        logger.info("Doing stuff with " + strMessage);
    }
}
