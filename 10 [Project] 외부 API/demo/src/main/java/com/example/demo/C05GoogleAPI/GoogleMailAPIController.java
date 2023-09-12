package com.example.demo.C05GoogleAPI;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

@Controller
@Slf4j
@RequestMapping("/google/mail")
public class GoogleMailAPIController {

    //[참고] https://velog.io/@tjddus0302/Spring-Boot-%EB%A9%94%EC%9D%BC-%EB%B0%9C%EC%86%A1-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-Gmail
    @GetMapping("/request")
    public void sendmail(String email){
        log.info("GET /google/mail/request");
    
        //메일설정
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("jwg135790@gmail.com");
        mailSender.setPassword("GOOGLE APPLICATION PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        mailSender.setJavaMailProperties(props);

        //난수값생성
        String tmpPassword = (int)(Math.random()*10000000)+"";

        //본문내용 설정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[WEB_TEST]임시패스워드");
        message.setText(tmpPassword);
        
        //발송
        mailSender.send(message);

    }


}
