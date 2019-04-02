package com.adam.consumerservice.config;

import com.adam.consumerservice.QueueConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author adambchouti on 2019-04-02.
 * @project consumer-service
 */
@Configuration
public class RabbitConfiguration {

    private static final String LISTENER_METHOD = "receiveMessage";

    @Value("${queue.name}")
    private String queueName;

    @Value("${fanout.exchange}")
    private String fanoutExchange;

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }
}
