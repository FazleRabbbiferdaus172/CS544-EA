package repositories;

import domain.Appointment;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AppointmentRepository extends JpaRepositoryImplementation<Appointment, Long> {
}
