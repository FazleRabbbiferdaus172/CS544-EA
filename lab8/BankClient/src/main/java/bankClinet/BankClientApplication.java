package bankClinet;

import bankClinet.domain.AccountDTO;
import bankClinet.domain.TransactionDTO;
import bankClinet.domain.TransferDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankClientApplication implements CommandLineRunner {
	private static final String SERVER_URL = "http://localhost:8080/accounts";
	private static final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Starting Bank Client ---");

		// Attempt a withdrawal that will FAIL
		try {
			TransactionDTO withdrawRequest = new TransactionDTO(1263862, 1000000000);
			restTemplate.postForObject(SERVER_URL + "/1263862/withdraw", withdrawRequest, AccountDTO.class);
		} catch (Exception e) {
			System.out.println("   - FAILED as expected.");
		}

		// Perform a successful transfer from Alice to Bob
		System.out.println("\n3. Transferring $200");
		TransferDTO transferRequest = new TransferDTO(4253892, 1263862,  200.00, "transfer");
		restTemplate.postForLocation(SERVER_URL + "/transfer", transferRequest);
		System.out.println("   - Transfer successful.");

		System.out.println("\n4. Checking final balances...");
		AccountDTO aAccount = restTemplate.getForObject(SERVER_URL + "/1263862", AccountDTO.class);
		AccountDTO bAccount = restTemplate.getForObject(SERVER_URL + "/4253892", AccountDTO.class);
		System.out.println("   - A new balance: $" + aAccount.getBalance());
		System.out.println("   - B new balance: $" + bAccount.getBalance());

		System.out.println("\n--- Bank Client Finished ---");
	}

}
