package app;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public School(String name) {
        this.name = name;
    }
}