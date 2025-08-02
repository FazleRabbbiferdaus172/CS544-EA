package bank.controller;

import bank.service.AccountService;
import bank.service.dto.AccountDTO;
import bank.service.dto.TransactionDTO;
import bank.service.dto.TransferDTO;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Collection<AccountDTO>> getAccount() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable long accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable long accountNumber, @RequestBody TransactionDTO request) {
        accountService.deposit(accountNumber, request.amount());
        AccountDTO acc = accountService.getAccount(accountNumber);
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable long accountNumber, @RequestBody TransactionDTO request) {
        accountService.withdraw(accountNumber, request.amount());
        AccountDTO acc = accountService.getAccount(accountNumber);
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@RequestBody TransferDTO request) {
        accountService.transferFunds(request.fromAccountNumber(), request.toAccountNumber(), request.amount(), request.description());
        return ResponseEntity.ok().build();
    }
}
