package top.xujie.tools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 */
@Configuration
@Slf4j
public class ListenerRouting {
    private static final String ROUTING_QUEUE_1 = "routing_queue_1";
    private static final String ROUTING_QUEUE_2 = "routing_queue_2";
    private static final String DIRECT_EXCHANGE = "direct_exchange";

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

}
