package com.linux.controller;

import com.linux.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class MyComtroller {
    @Autowired
    private MailService mailService;
    @PostMapping("")
    public String hello(String to,String subject,String text){
        mailService.sendSimolMail(to,subject,text);

        return  "邮件发送完毕";
    }
    @PostMapping("/html")
    public String html(String to,String subject,String text,String file) throws MessagingException {
       mailService.sendSimolMailHTML(to,subject,text,file);

        return  "HTML邮件发送完毕";
    }
}
