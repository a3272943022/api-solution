package com.example.apisolution.handle.Receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听-消费掉数据
 *
 * @author: key
 * @DATE: 2019/11/20 14 : 24
 * @CLASSNAME TestExchangeReceiver
 */
@Component
public class TestExchangeReceiver {

    /**
     * 扇形交换机
     *
     * @param message
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue1.fanou}")
    @RabbitHandler
    public void fanouQueue1Consumption(Message message, Channel channel) throws Exception {
        try {

            System.out.println("消息被消费-queue1-" + message.getBody().toString());
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            // 处理消息失败，将消息重新放回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }

    @RabbitListener(queues = "${spring.rabbitmq.queue2.fanou}")
    @RabbitHandler
    public void fanouQueue2Consumption(Message message, Channel channel) throws Exception {
        try {

            System.out.println("消息被消费-queue1-" + message.getBody().toString());
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            // 处理消息失败，将消息重新放回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    /**
     * 主体交换机
     */
    //会员监听
    @RabbitListener(queues = "${spring.rabbit.mq.member.register.queue.name}")
    @RabbitHandler
    public void fanouQueue1ConsumptionTopi(Message message, Channel channel) throws Exception {
        try {
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            // 处理消息失败，将消息重新放回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
        System.out.println("消息被消费-会员-" + message.getBody().toString());
    }

    //邮箱监听
    @RabbitListener(queues = "${spring.rabbitmq.topic.mail.queue.name}")
    @RabbitHandler
    public void fanouQueue2ConsumptionTopi(Message message, Channel channel) throws Exception {
        try {
            // 消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            // 处理消息失败，将消息重新放回队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
        System.out.println("消息被消费-邮箱-" + message.getBody().toString());
    }
}
