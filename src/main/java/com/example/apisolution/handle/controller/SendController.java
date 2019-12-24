package com.example.apisolution.handle.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *直连交换机
 * @author: key
 * @DATE: 2019/11/11 14 : 34
 * @CLASSNAME SendController
 */
@RestController
public class SendController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.routeKey}")
    public  String ROUTING_KEY;
    @Value("${mq.defaultExchange}")
    public  String EXCHANGE_NAME;

    @RequestMapping("/send")
    public String send() {
        long content = System.currentTimeMillis();
        amqpTemplate.convertAndSend(EXCHANGE_NAME,//exchange
                ROUTING_KEY,//routingKey
                content); //消息唯一id
        return "success";
    }

    @RequestMapping("get")
    public Object getText() {
        System.out.println("进入");
        return "ok";
    }

}
