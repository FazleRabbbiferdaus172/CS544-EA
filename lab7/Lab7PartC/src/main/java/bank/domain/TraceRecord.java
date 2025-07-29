package bank.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class TraceRecord {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateTime;
    private String message;

    public TraceRecord(String message) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
    }
}
