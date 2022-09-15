package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Passport;
import peaksoft.validation.Password;
import peaksoft.validation.PhoneNumberValid;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
public class PersonRequest {

    private String firstName;
    private String lastName;
    private int age;
    @PhoneNumberValid
    private String phoneNumber;
    @Email
    private String email;
    @Password
    private String password;
    private String inn;
    private String citizen;


}
