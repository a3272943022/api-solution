package com.example.apisolution.handle.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 扇形交换机使用
 * @author: key
 * @DATE: 2019/11/20 14 : 20
 * @CLASSNAME FanoutExchangeController
 */
@RestController
@RequestMapping(value = "/fanout")
public class FanoutExchangeController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.fanou}")
    private String fanouExchange;

    /**
     * 扇形交换机
     *
     * 生产数据
     *
     * @return
     */
    @RequestMapping("/text")
    public Object getExchangeText() {
        rabbitTemplate.convertAndSend(fanouExchange, "", "你好呀");
        return "生产";
    }




    /**
     *
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
     * 使用的是key键模糊匹配的方式来做的
     *
     * 主体交换机
     */
    @RequestMapping(value = "/topi")
    public Object getExchangeTopi(){
        rabbitTemplate.convertAndSend(topicExchange, topicExchangeRouteKey, "你好呀");
        return null;
    }


}
