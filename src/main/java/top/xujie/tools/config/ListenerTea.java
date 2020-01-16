package top.xujie.tools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xujie
 */
@Component
@Slf4j
public class ListenerTea {

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
