package rest.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest.models.Account;
import rest.repo.AccountRepo;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountRepo accountRepo;

    public UserDetailsServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepo.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(account.getLogin(), account.getPassword(), emptyList());
    }

}
