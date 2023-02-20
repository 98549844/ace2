/*
package com.restController;

import com.config.RabbitMQConfig;
import com.models.entity.Users;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.SleepUtil;

import java.util.List;

*/
/**
 * @Classname: RabbitRestController
 * @Date: 8/5/2021 12:10 上午
 * @Author: garlam
 * @Description:
 *//*


@RestController
@RequestMapping("/rest/rabbitmq")
@Api(tags = "rabbitmq")
public class RabbitMQRestController {
    private final static Logger log = LogManager.getLogger(RabbitMQRestController.class.getName());

    private final AmqpTemplate amqpTemplate;
    private UsersService usersService;

    @Autowired
    public RabbitMQRestController(UsersService usersService, AmqpTemplate amqpTemplate) {
        this.usersService = usersService;
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(method = RequestMethod.GET, value = "direct/send")
    public void sendDirectQueue() {
        String ace = "[ ACE ]";
        for (int i = 0; i < 50; i++) {
            log.info(ace + "  " + "version::" + i);
            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, ace + "  " + "version::" + i);
            SleepUtil.sleep(1);
        }
        List<Users> users = usersService.findAll();
        for (Users u : users) {
            this.amqpTemplate.convertAndSend(u);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "direct/send/user")
    public void sendDirectUser() {
        List<Users> users = usersService.findAll();
        for (Users u : users) {
          //  log.info("user: {}", u.getUsername());
            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, u);
            SleepUtil.sleep(1);
        }
    }

    //发送者和接收者的queue name必须一致，不然不能接收
    //queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(String msg) {
        SleepUtil.sleep(3);
        log.info("【receiverDirectQueue监听到消息】" + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(Users users) {
        SleepUtil.sleep(3);
        log.info("【receiverDirectQueue监听到消息】" + users.getUsername());
    }

}

*/
