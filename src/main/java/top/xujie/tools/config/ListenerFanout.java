package top.xujie.tools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujie
 * 订阅/发布模式
 */
@Configuration
@Slf4j
public class ListenerFanout {


    private static final String PS_QUEUE_1 = "ps_queue_1";
    private static final String PS_QUEUE_2 = "ps_queue_2";
    private static final String FANOUT_EXCHANGE = "fanout_exchange";

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


}
