package com.example.apisolution.handle.Receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: key
 * @DATE: 2019/11/11 14 : 36
 * @CLASSNAME Receiver1
 */
@Component
public class Receiver1 {

    /**
     * @RabbitListener 消息监听，可配置交换机、队列、路由key
     * 该注解会创建队列和交互机 并建立绑定关系
     * @RabbitHandler 标识此方法如果有消息过来，消费者要调用这个方法
     * @Payload 消息体
     * @Headers 消息头
     * @param
     */
    @RabbitListener(queues = "${mq.queue}")
    @RabbitHandler
    public void getReceiverl(long str) {
        System.out.println(str+"_______消费");
    }
}
