package top.xujie.tools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.xujie.tools.config.Sender;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xujie
 */
@RestController
@RequestMapping("/rabbitmq")
@Slf4j
public class RabbitmqController {

    @Autowired
    private Sender sender;

    @RequestMapping("/tea")
    @ResponseBody
    public String sender() throws InterruptedException {
        System.out.println("send string:hello world");
        sender.send("hello world");
        return "sending...";
    }

    @RequestMapping("/fanout")
    @ResponseBody
    public String fanout() throws Exception {
        try {
            sender.fanoutSend("fanout消息发布测试");
            return "success";
        } catch (Exception e) {
            // TODO: handle exception
            log.error("mq发送消息失败:" + e.getMessage(), e);
        }

        return "消息发送成功!";

    }

    @ResponseBody
    @RequestMapping("/direct")
    public String direct() {

        for (int i = 0; i < 10; i++) {
            sender.directSend("order", "order消息");
            sender.directSend("user", "user消息");
        }
        return "消息发送成功!";
    }

    @ResponseBody
    @RequestMapping("/topic")
    public String topic() {
        sender.topicSend(null, null);
        return null;
    }


}
