package com.linux.service;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.FileSystem;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender javaMailSender;
    /**
     *
     * @param to   收件人
     * @param subject  主题
     * @param content  内容
     */
    public void sendSimolMail(String to,String subject,String content){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(to);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(content);
        simpleMessage.setFrom(from);
        javaMailSender.send(simpleMessage);
    }
    public void sendSimolMailHTML(String to,String subject,String content,String filePath) throws MessagingException {
        // MimeMessage 复杂邮件模板，支持文本，附件HTMl、图片等
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //处理MimeMessage的辅助类
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(content);
        helper.setSubject(subject);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        String fileName = fileSystemResource.getFilename();
        helper.addAttachment(fileName,fileSystemResource);
        javaMailSender.send(mimeMessage);
    }
}
