package news.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import news.mail.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${MAIL_USER}")
    private String mailHost;

//    public void sendEmail(String to, String subject, String news) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//
//        mail.setTo(to);
//        mail.setSubject(subject);
//        mail.setText(news);
//        mail.setFrom(mailHost);
//
//        javaMailSender.send(mail);
//    }

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(mailHost);
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText(htmlBody, true);


        javaMailSender.send(mimeMessage);
    }
}
