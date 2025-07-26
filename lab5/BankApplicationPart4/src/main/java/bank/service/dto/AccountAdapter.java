package bank.service.dto;

import bank.domain.Account;
import bank.domain.AccountEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO){
        Account ac =  new Account(accountDTO.accountnumber());
        ac.setCustomer(accountDTO.customer());
        Collection<AccountEntry> enl = accountDTO.entryList();
        for(AccountEntry en : enl) {
            ac.getEntryList().add(en);
        }
        return ac;
    }
    public static AccountDTO getAccountDTOFromAccount(Account account){
        return new AccountDTO(account.getAccountnumber(),
                account.getEntryList(),
                account.getCustomer());
    }
    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts){
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
        for (Account account: accounts){
            accountDTOs.add(getAccountDTOFromAccount(account));
        }
        return accountDTOs;
    }
}
