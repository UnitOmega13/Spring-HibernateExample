package service.Impl;

import entity.OrderDetails;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.MailService;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Repository
public class MailServiceImpl implements MailService {
    private static final String userNmae = "testu0513@gmail.com";
    private static final String password = "490401Neko";
    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Transactional
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }

    @Transactional
    public Session getSession() {
        return Session.getInstance(getProperties(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userNmae, password);
                    }
                });
    }

    @Transactional
    @Override
    public void sendConfirmCode(OrderDetails orderDetails) {
        Session session = getSession();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userNmae));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(orderDetails.getEmail())
            );
            message.setSubject("Code for confirmation order");
            message.setText(String.valueOf(orderDetails.getDigitalCode()));
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Send Code ERROR!!!");
        }
    }
}
