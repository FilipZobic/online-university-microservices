package rs.singidunum.emailservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import rs.singidunum.emailservice.dto.EmailMessageDto;
import rs.singidunum.emailservice.utility.EmailUtil;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private final JavaMailSenderImpl javaMailSender;

    public RabbitMqReceiver(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(EmailMessageDto emailMessage) {
        SimpleMailMessage simpleMailMessage = EmailUtil.generateMailMessage(
                emailMessage.getSubject(),
                emailMessage.getBody(),
                emailMessage.getRecipient(),
                emailMessage.getSender());
        javaMailSender.send(simpleMailMessage);
    }

}
