package rs.singidunum.userservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailMessageDto implements Serializable {

    private String sender;

    private String recipient;

    private String subject;

    private String body;
}
