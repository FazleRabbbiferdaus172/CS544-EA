package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private GradeRepository gradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Department cs = departmentRepository.save(new Department("Computer Science"));
		Department physics = departmentRepository.save(new Department("Physics"));

		Course jpa = courseRepository.save(new Course("JPA and Hibernate"));
		Course qm = courseRepository.save(new Course("Quantum Mechanics"));

		Student alice = studentRepository.save(new Student("Alice", "S101", cs));
		Student bob = studentRepository.save(new Student("Bob", "S102", cs));
		Student charlie = studentRepository.save(new Student("Charlie", "P201", physics));

		gradeRepository.save(new Grade("A", alice, jpa));
		gradeRepository.save(new Grade("B", bob, jpa));
		gradeRepository.save(new Grade("A-", charlie, qm));


		studentRepository.findByDepartmentName("Computer Science")
				.forEach(s -> System.out.println(" - " + s.getName()));

		studentRepository.findDistinctByGradesCourseName("JPA and Hibernate")
				.forEach(s -> System.out.println(" - " + s.getName()));

		System.out.println("Students in Computer Science:");
		studentRepository.findStudentsByDepartmentNameOptimized("Computer Science")
				.forEach(s -> System.out.println(" - " + s.getName() + " (" + s.getDepartment().getName() + ")"));

		System.out.println("Students who took Quantum Mechanics:");
		studentRepository.findStudentsByCourseNameOptimized("Quantum Mechanics")
				.forEach(s -> System.out.println(" - " + s.getName() + " (" + s.getDepartment().getName() + ")"));

	}

}

