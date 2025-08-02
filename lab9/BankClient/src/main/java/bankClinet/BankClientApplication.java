package bankClinet;

import bankClinet.domain.AccountDTO;
import bankClinet.domain.TransactionDTO;
import bankClinet.domain.TransferDTO;
import bankClinet.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankClientApplication implements CommandLineRunner {
	@Autowired
	Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Starting Bank Client ---");

		sender.send("bank","CREATE_ACCOUNT,1003,Charlie");
		sender.send("bank","DEPOSIT,1003,500.0");
		sender.send("bank","WITHDRAW,1003,150.0");

		System.out.println("\n--- Bank Client Finished ---");
	}

}
