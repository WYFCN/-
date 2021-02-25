//package com.example.newspape.amqp.config;
package com.example.newspape.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MyAmqpConfig {
    public final static String QUEUE_NAME_01 = "Literature";
    public final static String QUEUE_NAME_02 = "Love";
    public final static String QUEUE_NAME_03 = "Philosophy";
    public final static String QUEUE_NAME_04 = "Cartoon";

    public final static String EXCHANGE_NAME = "spring-boot-exchange";

    public final static String ROUTING_KEY_01 = "#.literature";
    public final static String ROUTING_KEY_02 = "#.love";
    public final static String ROUTING_KEY_03 = "#.philosophy";
    public final static String ROUTING_KEY_04 = "#.cartoon";
    // 创建队列
    @Bean
    public Queue queue_01() {
        return new Queue(QUEUE_NAME_01);
    }
    @Bean
    public Queue queue_02() {
        return new Queue(QUEUE_NAME_02);
    }
    @Bean
    public Queue queue_03() {
        return new Queue(QUEUE_NAME_03);
    }
    @Bean
    public Queue queue_04() {
        return new Queue(QUEUE_NAME_04);
    }
    // 创建一个 topic 类型的交换器
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding_01(Queue queue_01, TopicExchange exchange) {
        return BindingBuilder.bind(queue_01).to(exchange).with(ROUTING_KEY_01);
    }
    @Bean
    public Binding binding_02(Queue queue_02, TopicExchange exchange) {
        return BindingBuilder.bind(queue_02).to(exchange).with(ROUTING_KEY_02);
    }
    @Bean
    public Binding binding_03(Queue queue_03, TopicExchange exchange) {
        return BindingBuilder.bind(queue_03).to(exchange).with(ROUTING_KEY_03);
    }
    @Bean
    public Binding binding_04(Queue queue_04, TopicExchange exchange) {
        return BindingBuilder.bind(queue_04).to(exchange).with(ROUTING_KEY_04);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("172.20.10.9", 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

}
