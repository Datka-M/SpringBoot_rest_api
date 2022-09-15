package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonResponse {

    private Long id;
    private String fullName;
    private String email;
    private int age;
    private String phoneNumber;
    private String password;


    public PersonResponse(Long id, String fullName, int age, String email, String phoneNumber ) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

}
