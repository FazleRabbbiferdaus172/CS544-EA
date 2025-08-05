package accounts.repository;
import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByAccountHolder_thenReturnAccount() {
        // 1. Setup: Create and save an Account entity
        Account account = new Account("12345", 1000.0, "John Doe");
        entityManager.persist(account);
        entityManager.flush();

        // 2. Action: Call the repository method
        Account found = accountRepository.findByAccountHolder("John Doe");

        // 3. Assertion: Verify the result
        assertThat(found.getAccountHolder()).isEqualTo(account.getAccountHolder());
    }
}
