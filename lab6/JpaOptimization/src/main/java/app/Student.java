package app;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String emailaddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;


    public Student(String firstname, String lastname, String emailaddress, School school) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.school = school;
    }
}
