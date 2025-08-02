package bank.dao;

import java.util.*;

import bank.domain.Account;
import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO implements IAccountDAO {
	@Autowired
	private AccountRepository accountRepository;

	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
		accountRepository.save(account); // add the new
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
		Account accountexist = loadAccount(account.getAccountnumber());
		if (accountexist != null) {
			accountRepository.save(account); // add the new
		}

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		Optional<Account> ac = accountRepository.findById(accountnumber);
		if (ac.isPresent()){return ac.get();}
 		return null;
	}

	public Collection<Account> getAccounts() {
		return accountRepository.findAll();
	}

}
