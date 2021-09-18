package rs.singidunum.emailservice.utility;

import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {

    public static SimpleMailMessage generateMailMessage(String subject, String message, String recipient, String sender) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(recipient);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        emailMessage.setFrom(sender);
        return emailMessage;
    }
}