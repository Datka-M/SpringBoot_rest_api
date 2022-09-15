package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(generator = "person_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_gen",sequenceName = "person_seq",allocationSize = 1)
    private Long id;
    private String fullName;
    private int age;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Passport passport;

}
