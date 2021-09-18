package rs.singidunum.userservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import rs.singidunum.userservice.dto.EmailMessageDto;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.service.EmailMessagingQueServiceAmqpImplementation;
import rs.singidunum.userservice.service.UserService;

import java.util.Locale;

@Component
public class NewTokenGeneratedListener extends GenericMailListener implements ApplicationListener<NewTokenGeneratedEvent> {

  @Override
  @Autowired
  protected void autowireAttributes(EmailMessagingQueServiceAmqpImplementation javaMailSender, MessageSource messages, UserService userService) {
    super.autowireAttributes(javaMailSender, messages, userService);
  }

  @Override
  public void onApplicationEvent(NewTokenGeneratedEvent event) {
    User user = event.getUser();
    String recipient = user.getEmail();
    String subject = "New Confirmation Link";
    String confirmationUrl =  "/api/registrationConfirmation?token=" + event.getVerificationToken().getId().toString();
    String message = "Username: " + user.getUsername() + "\n\n";
    message += messages.getMessage("message.regReSendSuccLink", null, Locale.ENGLISH);
    message += "\r\n" + this.domain + confirmationUrl;
    EmailMessageDto emailMessageDto = new EmailMessageDto("sender@gmail.com", recipient, subject, message);
    emailService.send(emailMessageDto);
  }
}
