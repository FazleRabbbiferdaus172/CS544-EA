package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDepartmentName(String departmentName);
    List<Student> findDistinctByGradesCourseName(String courseName);

    @Query("SELECT s FROM Student s JOIN FETCH s.department d WHERE d.name = :departmentName")
    List<Student> findStudentsByDepartmentNameOptimized(@Param("departmentName") String departmentName);

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.department " +
            "JOIN s.grades g JOIN g.course c " +
            "WHERE c.name = :courseName")
    List<Student> findStudentsByCourseNameOptimized(@Param("courseName") String courseName);
}
