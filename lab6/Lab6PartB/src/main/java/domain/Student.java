package domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String studentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Grade> grades = new ArrayList<>();

    public Student(String name, String studentNumber, Department department) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.department = department;
    }
}
