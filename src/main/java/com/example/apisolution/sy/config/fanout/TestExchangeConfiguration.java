package com.example.apisolution.sy.config.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbatMq扇形交换机
 *
 * @author: key
 * @DATE: 2019/11/20 10 : 29
 * @CLASSNAME FanoutExchange
 */
@Configuration
public class TestExchangeConfiguration {
    @Value("${spring.rabbitmq.exchange.fanou}")
    private String fanouExchange;
    @Value("${spring.rabbitmq.queue1.fanou}")
    private String fanouQueue1;
    @Value("${spring.rabbitmq.queue2.fanou}")
    private String fanouQueue2;

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanouExchange);
    }

    //创建队列1
    @Bean
    public Queue queue1() {
        return new Queue(fanouQueue1,true);
    }

    //创建队列2
    @Bean
    public Queue queue2() {
        return new Queue(fanouQueue2,true);
    }

    /**
     * 绑定队列一到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding bingQueue1ToExchange() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    /**
     * 绑定队列二到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding bingQueue2ToExchange() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }


}
