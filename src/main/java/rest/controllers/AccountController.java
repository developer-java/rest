package rest.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rest.exceptions.NotFoundException;
import rest.models.Account;
import rest.repo.AccountRepo;
import rest.security.SecurityUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

import static rest.security.SecurityUtils.HEADER_STRING;
import static rest.security.SecurityUtils.TOKEN_PREFIX;
import static rest.utils.BeanUtilsHelper.getNullPropertyNames;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountController(AccountRepo accountRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepo = accountRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public void signUp(@RequestBody Account account, HttpServletResponse res) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepo.save(account);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(account.getLogin()));
    }

    @GetMapping
    public Collection<Account> getAllAccounts() {
            return accountRepo.findAll();
    }

    @GetMapping("{id}")
    public Account getAccount(@PathVariable(value = "id") Long id) {
        Optional<Account> optionalAccount = accountRepo.findById(id);

        if(!optionalAccount.isPresent()) {
            throw new NotFoundException("Account " + id + " not found");
        }

        return optionalAccount.get();
    }

    @PutMapping("{id}")
    public ResponseEntity<Account> updateAccount(
            @RequestBody Account account,
            @PathVariable (value = "id") Long id
    ) {
        Optional<Account> optionalAccount = accountRepo.findById(id);

        if (!optionalAccount.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BeanUtils.copyProperties(account, optionalAccount.get(), getNullPropertyNames(account));

        accountRepo.save(optionalAccount.get());

        return new ResponseEntity<>(optionalAccount.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable (value = "id") Long id) {
        return accountRepo.findById(id).map(account -> {
            accountRepo.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("Account " + id + " not found"));
    }

}
