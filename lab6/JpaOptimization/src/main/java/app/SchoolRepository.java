package app;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("SELECT s FROM School s")
    List<School> findAllSchoolNames();

    @Query("SELECT DISTINCT s FROM School s LEFT JOIN FETCH s.students")
    List<School> findAllWithStudents();
}