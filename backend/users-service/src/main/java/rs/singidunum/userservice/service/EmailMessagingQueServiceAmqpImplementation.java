package rs.singidunum.userservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.singidunum.userservice.dto.EmailMessageDto;

@Service
public class EmailMessagingQueServiceAmqpImplementation {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailMessagingQueServiceAmqpImplementation(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.microservices.email.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.microservices.email.routingkey}")
    private String routingKey;

    public void send(EmailMessageDto user){
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}
