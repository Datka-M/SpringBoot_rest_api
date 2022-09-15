package peaksoft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "passports")
@Getter
@Setter
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "passport_gen")
    @SequenceGenerator(name = "passport_gen",sequenceName = "passport_seq",allocationSize = 1)
    private Long id;
    private String inn;
    private String citizen;
}
