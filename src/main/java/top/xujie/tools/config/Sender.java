package top.xujie.tools.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xujie
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     *     简单发送队列
     */
    public static final String SIMPLE_QUEUE = "tea";
    /**
     * 发布/订阅模式
     */
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    /**
     * //路由模式
     */
    public static final String DIRECT_EXCHANGE = "direct_exchange";

    /**
     * //主题模式
     */
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    /**
     * 简单模式发送 String类型消息
     *
     * @param message
     */

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(SIMPLE_QUEUE, message);
    }

    /**
     * 简单模式发送 Map类型消息
     *
     * @param map
     */

    public void sendMapMessage(Map map) {

        this.rabbitTemplate.convertAndSend(SIMPLE_QUEUE, map);
    }

    /**
     * 发布/订阅模式发送
     *
     * @param message
     */
    public void fanoutSend(String message) {
        this.rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", message);
    }

    /**
     * 路由模式发送
     *
     * @param message
     */
    public void directSend(String routingKey, String message) {
        this.rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, routingKey, message);
    }

    /**
     * 主题模式发送
     *
     * @param routingKey
     * @param message
     */
    public void topicSend(String routingKey, String message) {
        this.rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, routingKey, message);
    }

}
