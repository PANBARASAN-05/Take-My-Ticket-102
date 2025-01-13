package com.example.email_scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class EmailSchedulerApplication {

    @Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(EmailSchedulerApplication.class, args);
    }

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com");
        message.setSubject("Scheduled Email");
        message.setText("This is a scheduled email sent using Spring Boot.");

        javaMailSender.send(message);
        System.out.println("Email sent successfully.");
    }
}
