package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class kafkaService {
    @Autowired
    private AccountService accountService;

    @KafkaListener(topics = {"bank"})
    public void receive(@Payload String commandString) {
        System.out.println("Receiver received message= "+ commandString);
        String[] parts = commandString.split(",");
        String commandType = parts[0];
        long accountNumber;
        double amount;

        switch (commandType) {
            case "CREATE_ACCOUNT":
                if (parts.length < 3) {
                    System.err.println("Invalid CREATE_ACCOUNT command.");
                    return;
                }
                accountNumber = Long.parseLong(parts[1]);
                String customerName = parts[2];
                accountService.createAccount(accountNumber, customerName);
                break;

            case "DEPOSIT":
                accountNumber = Long.parseLong(parts[1]);
                amount = Double.parseDouble(parts[2]);
                accountService.deposit(accountNumber, amount);
                break;

            case "WITHDRAW":
                accountNumber = Long.parseLong(parts[1]);
                amount = Double.parseDouble(parts[2]);
                accountService.withdraw(accountNumber, amount);
                break;

            default:
                System.out.println("Unknown command type: " + commandType);
        }
    }
}
