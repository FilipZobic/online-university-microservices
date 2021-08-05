package rs.singidunum.userservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import rs.singidunum.userservice.dto.EmailMessageDto;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.model.VerificationToken;
import rs.singidunum.userservice.service.EmailMessagingQueServiceAmqpImplementation;
import rs.singidunum.userservice.service.UserService;

import java.util.Locale;

@Component
public class RegistrationListener extends GenericMailListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  @Autowired
  @Override
  protected void autowireAttributes(EmailMessagingQueServiceAmqpImplementation emailService, MessageSource messages, UserService userService) {
    super.autowireAttributes(emailService, messages, userService);
  }

  @Autowired
  private EmailMessagingQueServiceAmqpImplementation emailService;

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    User user = event.getUser();

    String recipient = user.getEmail();
    String message = "Username: " + user.getUsername();
    message += "\nPassword: " + event.getPassword() + "\n\n";


    String subject = "Registration";
    if (!user.getEnabled()) {
      VerificationToken token = userService.createVerificationToken(user);
      subject = "Confirm Registration";
      String confirmationUrl = event.getUrl() + "/api/registrationConfirmation?token=" + token.getId().toString();
      message += messages.getMessage("message.regSuccLink", null, Locale.ENGLISH);
      message += "\r\n" + this.domain + confirmationUrl;
    }

    EmailMessageDto emailMessageDto = new EmailMessageDto("noreply@localhost.com", recipient, subject, message);
    emailService.send(emailMessageDto);
  }
}
