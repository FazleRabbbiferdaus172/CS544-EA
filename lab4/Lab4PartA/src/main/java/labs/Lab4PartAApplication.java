package labs;

import labs.domain.*;
import labs.repository.BookRepository;
import labs.repository.DepartmentRepository;
import labs.repository.PassengerRepository;
import labs.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class Lab4PartAApplication implements CommandLineRunner {
	@Autowired
	private DepartmentRepository depRep;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab4PartAApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception{
		System.out.println("HI");
		Department dep1 = new Department("dep");
		List<Employee> emps = new ArrayList<>();
		Employee emp1 = new Employee("Emp1", dep1);
		Employee emp2 = new Employee("Emp2", dep1);
		Employee emp3 = new Employee("Emp3", dep1);
		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);
		dep1.setEmployees(emps);
		depRep.save(dep1);
		List<Department> deps = depRep.findAll();
		for (Department dep : deps) {
			System.out.println(dep);
		}

		Publisher publisher = new Publisher("auth1");
		Book bookWithPublisher = new Book("123", "book1", "auth1");
		bookWithPublisher.setPublisher(publisher);
		bookRepository.save(bookWithPublisher);

		Book bookWithoutPublisher = new Book("456", "book2", "auth2");
		bookRepository.save(bookWithoutPublisher);
		List<Book> books = bookRepository.findAll();
		for (Book book : books) {
			System.out.println(book);
		}


		Passenger passenger = new Passenger("John Doe");
		Flight flight1 = new Flight("UA100", "New York", "London", LocalDate.now().plusDays(10));
		Flight flight2 = new Flight("BA200", "London", "Paris", LocalDate.now().plusDays(15));
		Flight flight3 = new Flight("AF300", "Paris", "New York", LocalDate.now().plusDays(20));
		passenger.getFlights().add(flight1);
		passenger.getFlights().add(flight2);
		passenger.getFlights().add(flight3);
		passengerRepository.save(passenger);
		Passenger passenger1 = passengerRepository.findById(passenger.getId()).orElseThrow();
		System.out.println("Passenger: "+passenger1);
		List<Flight> flights = passenger1.getFlights();
		for (Flight fl:flights) {
			System.out.println("Flight : "+fl);
		}

		School school = new School("school1");
		Student student1 = new Student("s1", "s", "1");
		Student student2 = new Student("s2", "s", "2");
		Student student3 = new Student("s3", "s", "3");

		school.getStudents().put(student1.getStudentId(), student1);
		school.getStudents().put(student2.getStudentId(), student2);
		school.getStudents().put(student3.getStudentId(), student3);

		schoolRepository.save(school);
		School sf = schoolRepository.findById(school.getId()).orElseThrow();
		System.out.println("Fetched school: "+ sf);

		String studentIdToFind = "s1";
		Student ss = sf.getStudents().get(studentIdToFind);
		System.out.println(ss);
	}

}
