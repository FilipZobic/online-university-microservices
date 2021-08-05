package rs.singidunum.userservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import rs.singidunum.userservice.model.User;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  private Locale locale;
  private String password;
  private User user;
  private String url;

  public OnRegistrationCompleteEvent(User user, String password, Locale locale, String url) {
    super(user);

    this.user = user;
    this.password = password;
    this.locale = locale;
    this.url = url;
  }
}
