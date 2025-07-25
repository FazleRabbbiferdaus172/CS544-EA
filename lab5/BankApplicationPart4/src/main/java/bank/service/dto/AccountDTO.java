package bank.service.dto;

import bank.domain.AccountEntry;
import bank.domain.Customer;

import java.util.Collection;

public record AccountDTO(Long accountnumber, Collection<AccountEntry> entryList, Customer customer) {
    public double getBalance() {
        double balance=0;
        for (AccountEntry entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }
}
