package top.xujie.tools.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author xujie
 */
@Configuration
@Slf4j
@Component
public class RabbitMqConfig {


    private static final String ROUTING_QUEUE_1 = "routing_queue_1";
    private static final String ROUTING_QUEUE_2 = "routing_queue_2";
    private static final String DIRECT_EXCHANGE = "direct_exchange";

    private static final String PS_QUEUE_1 = "ps_queue_1";
    private static final String PS_QUEUE_2 = "ps_queue_2";
    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    private static final String TOPIC_QUEUE_1 = "topic_queue_1";
    private static final String TOPIC_QUEUE_2 = "topic_queue_2";
    private static final String TOPIC_EXCHANGE = "topic_exchange";
    private static final String TOPIC_ROUTINGKEY1 = "user.*";
    private static final String TOPIC_ROUTINGKEY2 = "order.#";


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

    @Bean
    public Queue psQueue1() {
        return new Queue(PS_QUEUE_1, true);
    }

    @Bean
    public Queue psQueue2() {
        return new Queue(PS_QUEUE_2, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(psQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(psQueue2()).to(fanoutExchange());
    }

    /**
     * 发布/订阅模式接收
     *
     * @param message
     */
    @RabbitListener(queues = PS_QUEUE_1)
    public void psReceive1(String message) {
        System.out.println(PS_QUEUE_1 + "接收消息:" + message);
        log.info(PS_QUEUE_1 + "接收消息:" + message);
    }

    @RabbitListener(queues = PS_QUEUE_2)
    public void psReceive2(String message) {
        System.out.println(PS_QUEUE_2 + "接收消息:" + message);
        log.info(PS_QUEUE_2 + "接收消息:" + message);
    }


    @Bean
    public Queue routingQueue1() {
        return new Queue(ROUTING_QUEUE_1, true);
    }

    @Bean
    public Queue routingQueue2() {
        return new Queue(ROUTING_QUEUE_2, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(routingQueue1()).to(directExchange()).with("user");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(routingQueue2()).to(directExchange()).with("order");
    }


    /**
     * 路由模式接收
     *
     * @param message
     */
    @RabbitListener(queues = ROUTING_QUEUE_1)
    public void routingReceive1(String message) {
        System.out.println(ROUTING_QUEUE_1 + "接收路由模式消息:" + message);
    }

    @RabbitListener(queues = ROUTING_QUEUE_2)
    public void routingReceive2(String message) {
        System.out.println(ROUTING_QUEUE_2 + "接收路由模式消息:" + message);
    }


    /**
     * 简单发送模式
     *
     * @return
     */
    @Bean
    public Queue teaQueue() {
        return new Queue("tea", true);
    }

    @RabbitListener(queues = "tea")
    @RabbitHandler
    public void process(@Payload String message) {
        log.info("Listener收到String类型mq消息:" + message);

    }

    @RabbitListener(queues = "tea")
    @RabbitHandler
    public void process1(@Payload Map map) {
        log.info("Listener收到Map类型mq消息:" + map);
    }


}
