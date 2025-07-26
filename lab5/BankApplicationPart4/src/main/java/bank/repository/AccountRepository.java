package bank.repository;

import bank.domain.Account;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepositoryImplementation<Account, Long> {
}
