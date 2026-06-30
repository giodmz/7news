package news.mail.component;

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
    public void consumeNews(NewsDTO obj) {
        System.out.println("Message received: " + obj);

        emailService.sendEmail(mailSender, "teste 1",
                obj.toString());
    }
}
