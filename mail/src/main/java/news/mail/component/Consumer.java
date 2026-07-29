package news.mail.component;

import jakarta.mail.MessagingException;
import news.mail.dto.NewsDTO;
import news.mail.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private EmailService emailService;

    @Value("${MAIL_SENDER}")
    private String mailSender;



    @RabbitListener(queues = "news.sent")
    public void consumeNews(NewsDTO obj) throws MessagingException {
        System.out.println("Message received: " + obj);

        String html = "<h1>" + obj.getTitle() + "</h1>" +
                "<p>" + obj.getContent() + "</p>" +
                "<img src='" + obj.getImageUrl() + "'/>";

        emailService.sendHtmlEmail(mailSender, "Email teste",
                html);
    }
}
