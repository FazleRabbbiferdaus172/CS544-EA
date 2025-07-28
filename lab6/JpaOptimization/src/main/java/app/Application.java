package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		insertCustomers();
//		retrieveCustomers();
//		updateCustomers();
		insertSchool();
		runSchoolOnlyRetrieval();
		runSchoolWithStudentsRetrieval();
	}

	private void insertCustomers() {
		for (int x=0; x<50000; x++) {
			Customer customer = new Customer("John Doe "+x);
			Account account = new Account("123"+x);
			customer.setAccount(account);
			customerRepository.save(customer);
			System.out.println("Inserting customer  "+x);
		}
	}

	private void retrieveCustomers() {
		System.out.println("Retrieving all customers ...");
		long start = System.currentTimeMillis();

		List<Customer> customers = customerRepository.findAllWithAccounts();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Customers took "+timeElapsed+" ms");
	}

	private void updateCustomers() {
		System.out.println("Change the name of all customers ...");
		long start = System.currentTimeMillis();

		List<Customer> customers = customerRepository.findAllWithAccounts();
		for(Customer c: customers){
			customerRepository.updateAllNames("James Bond");
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To change the name of all customers took "+timeElapsed+" ms");
	}

	private void runSchoolOnlyRetrieval() {
		long start = System.currentTimeMillis();
		List<School> schools = schoolRepository.findAll();
		long end = System.currentTimeMillis();
		System.out.printf("Fetching full School entities took: %d ms%n", (end - start));

		start = System.currentTimeMillis();
		List<School> schoolNames = schoolRepository.findAllSchoolNames();
		end = System.currentTimeMillis();
		System.out.printf("Fetching School names with DTO projection took: %d ms%n", (end - start));
	}

	private void runSchoolWithStudentsRetrieval() {
		long start = System.currentTimeMillis();
		List<School> schools = schoolRepository.findAll();
		schools.forEach(school -> school.getStudents().size());
		long end = System.currentTimeMillis();
		System.out.printf("Fetching with N+1 problem took: %d ms%n", (end - start));

		start = System.currentTimeMillis();
		List<School> schoolsWithStudents = schoolRepository.findAllWithStudents();
		end = System.currentTimeMillis();
		System.out.printf("Fetching with JOIN FETCH took: %d ms%n", (end - start));
	}

	private void insertSchool() {
		System.out.println("Seeding database... this may take a moment.");
		for (int i = 1; i <= 100; i++) {
			School school = new School("School " + i);
			schoolRepository.save(school);
			for (int j = 1; j <= 200; j++) {
				Student student = new Student("First" + j, "Last" + j, "student" + i + "_" + j + "@email.com", school);
				studentRepository.save(student);
			}
		}
		System.out.println("Database seeding complete.");
	}
}
