package rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.models.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByLogin(String login);

}
