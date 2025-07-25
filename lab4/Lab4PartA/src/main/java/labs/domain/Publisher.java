package labs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Publisher(String name) {
        this.name = name;
    }
}