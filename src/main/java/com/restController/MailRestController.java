package com.restController;

import com.models.common.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @Classname: MailRestController
 * @Date: 5/7/2021 1:28 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest")
@Api(tags = {"mail"})
public class MailRestController {
    private static final Logger log = LogManager.getLogger(MailRestController.class.getName());

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailRestController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @ApiOperation(value = "Sample mail")
    @PostMapping("/mail.html")
    public String sendEmail(@RequestBody Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(email.getTo()); // 接收地址
            message.setSubject(email.getSubject()); // 标题
            message.setText(email.getContent().toString()); // 内容
            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @PostMapping("/htmlEmail.html")
    @ApiOperation(value = "HTML mail")
    public String sendHtmlEmail(@RequestBody Email email) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email.getTo()); // 接收地址
            helper.setSubject(email.getSubject()); // 标题
            // 带HTML格式的内容
            StringBuffer sb = new StringBuffer(email.getContent());
            helper.setText(sb.toString(), true);
            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @PostMapping("/mailWithAttachments.html")
    @ApiOperation(value = "Mail with attachments")
    public String sendAttachmentsMail(@RequestBody Email email) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email.getTo()); // 接收地址
            helper.setSubject(email.getSubject()); // 标题
            helper.setText(email.getContent().toString()); // 内容
            // 传入附件
            File f = new File(email.getAttachmentsPath());
            FileSystemResource file = new FileSystemResource(f);
            helper.addAttachment(f.getName(), file);
            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }



}

