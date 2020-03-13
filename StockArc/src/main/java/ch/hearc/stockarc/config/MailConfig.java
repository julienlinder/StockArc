package ch.hearc.stockarc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * MailConfig
 */
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // mailSender.setHost("smtp.gmail.com");
        // mailSender.setPort(587);

        // mailSender.setUsername("gchriste96@gmail.com");
        // // mailSender.setPassword("6$V#UWnxXF#H9i5n0wr"); // TODO

        // Properties props = mailSender.getJavaMailProperties();
        // props.put("mail.transport.protocol", "smtp");
        // props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.debug", "true");

        return mailSender;
    }
}