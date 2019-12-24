package com.example.apisolution.sy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机
 *
 * @author: key
 * @DATE: 2019/11/20 17 : 19
 * @CLASSNAME MemberRegistrtRabbitMQConfiguration
 */
@Configuration
public class MemberRegistrtRabbitMQConfiguration {
    /**
     * 交换机
     */
    @Value("${spring.rabbit.mq.topic.exchange.name}")
    private String topicExchange;
    /**
     * 交换机路由
     */
    @Value("${spring.rabbitmq.topic.exchange.route.key}")
    private String topicExchangeRouteKey;
    /**
     * 会员队列名称
     */
    @Value("${spring.rabbit.mq.member.register.queue.name}")
    private String registerQueueName;
    /**
     * 会员队列路由键
     */
    @Value("${spring.rabbitmq.topic.route.key}")
    private String topicRouteKey;
    /**
     * 邮箱队列名称
     */
    @Value("${spring.rabbitmq.topic.mail.queue.name}")
    private String topicMailQueueName;
    /**
     * 邮箱队列路由键
     */
    @Value("${spring.rabbitmq.topic.mail.route.key}")
    private String tipicMailRouteKey;


    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        System.out.println("会员交换机创建成功");
        return  new TopicExchange(topicExchange);
    }

    /**
     * 创建会员队列实例，并持久化
     * @return
     */
   @Bean
    public Queue memberRegisterQueueName(){
        return  new Queue(registerQueueName,true);
    }

    /**
     * 创建邮箱队列，并持久化
     * @return
     */
    @Bean
    public Queue memberTopicMailQueueName(){
        return new Queue(topicMailQueueName,true);
    }

    /**
     *绑定队列
     * @return
     */
    @Bean
    public Binding memberRegisterQueueNameBinding(){
          return  BindingBuilder.bind(memberRegisterQueueName()).to(topicExchange()).with(topicRouteKey);
    }

    /**
     *绑定队列
     * @return
     */
    @Bean
    public Binding memberRegisterQueueNameBindings(){
        return  BindingBuilder.bind(memberTopicMailQueueName()).to(topicExchange()).with(tipicMailRouteKey);
    }
}
