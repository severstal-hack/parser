package org.severstal.parser.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.severstal.parser.config.RabbitMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableRabbit
@Configuration
public class RabbitMQConfiguration {
    @Autowired
    private RabbitMQConfig cfg;
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(this.cfg.getHostname());
        connectionFactory.setUsername(this.cfg.getUsername());
        connectionFactory.setPassword(this.cfg.getPassword());
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());

        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue mainQueue() {
        return new Queue("p-queue");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("p-exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(mainQueue()).to(fanoutExchange());
    }

}
