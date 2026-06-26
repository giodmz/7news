package notice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private final RabbitTemplate rabbitTemplate;

    public PublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String exchange, String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
