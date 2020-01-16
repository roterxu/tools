package top.xujie.tools.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author xujie
 */
@Configuration
@Slf4j
public class ListenerTopic {
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String TOPIC_ROUTINGKEY1 = "user.*";
    public static final String TOPIC_ROUTINGKEY2 = "order.#";


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue topicQueue1() {

        //第二个参数 是durable 队列持久化
        return new Queue(TOPIC_QUEUE_1, true);
    }

    @Bean
    public Queue topicQueue2() {

        //第二个参数 是durable 队列持久化
        return new Queue(TOPIC_QUEUE_2, true);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_ROUTINGKEY1);
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_ROUTINGKEY2);
    }


    /**
     * 主题模式接收
     *
     * @param message
     */

    @RabbitListener(queues = TOPIC_QUEUE_1)
    public void topicReceive1(@Payload String message, Message mes, Channel channel) throws IOException {
        channel.basicAck(mes.getMessageProperties().getDeliveryTag(), false);
        System.out.println(TOPIC_QUEUE_1 + "接收消息:" + mes.getMessageProperties().getDeliveryTag() + message);
        log.info(TOPIC_QUEUE_1 + "接收消息id为:" + mes.getMessageProperties().getDeliveryTag() + "内容为: " + message);
    }

    @RabbitListener(queues = TOPIC_QUEUE_2)
    public void topicReceive2(@Payload String message, Message mes, Channel channel) throws IOException {

        channel.basicAck(mes.getMessageProperties().getDeliveryTag(), false);
        System.out.println(TOPIC_QUEUE_2 + "接收消息:" + mes.getMessageProperties().getDeliveryTag() + message);
        log.info(TOPIC_QUEUE_2 + "接收消息id为" + mes.getMessageProperties().getDeliveryTag() + "内容为: " + message);
    }

}
