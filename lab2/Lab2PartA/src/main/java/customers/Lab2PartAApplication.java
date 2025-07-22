package customers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class Lab2PartAApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lab2PartAApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception{
		System.out.println("ok");
	}

}
