package rs.singidunum.userservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountryDto {

    private String name;

    private String alpha2Code;
}
