package rs.singidunum.userservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.model.VerificationToken;

import java.util.Locale;

@Getter
@Setter
public class NewTokenGeneratedEvent extends ApplicationEvent {

  private Locale locale;
  private User user;
  private VerificationToken verificationToken;

  public NewTokenGeneratedEvent(Locale locale, User user, VerificationToken verificationToken) {
    super(user);
    this.locale = locale;
    this.user = user;
    this.verificationToken = verificationToken;
  }
}
