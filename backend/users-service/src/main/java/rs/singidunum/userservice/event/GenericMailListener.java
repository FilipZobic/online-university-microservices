package rs.singidunum.userservice.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import rs.singidunum.userservice.service.EmailMessagingQueServiceAmqpImplementation;
import rs.singidunum.userservice.service.UserService;


public abstract class GenericMailListener {

  protected MessageSource messages;

  protected UserService userService;

  protected EmailMessagingQueServiceAmqpImplementation emailService;

  @Value("${link.value}")
  protected String domain;

  protected void autowireAttributes(EmailMessagingQueServiceAmqpImplementation emailService, MessageSource messages, UserService userService) {
    this.messages = messages;
    this.userService = userService;
    this.emailService = emailService;
  }
}
