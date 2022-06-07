package com.acmebank.account.config;

import com.acmebank.account.model.Account;
import com.acmebank.account.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AccountConfig {

    public final long ACCOUNT_INITIAL_BALANCE = 1_000_000;

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
        return args -> {
            // Create Account 12345678
            Account account1 = accountRepository.findByAccountNumber(12345678);
            if (account1 == null) {
                account1 = new Account()
                        .setAccountNumber(12345678)
                        .setEmail("12345678@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setFirstName("Zi-long")
                        .setLastName("QIU")
                        .setPhoneNumber("+85212345678")
                        .setBirthdate(LocalDate.of(1978, 1, 2))
                        .setBalance(ACCOUNT_INITIAL_BALANCE);
                accountRepository.save(account1);
            }

            // Create Account 88888888
            Account account2 = accountRepository.findByAccountNumber(88888888);
            if (account2 == null) {
                account2 = new Account()
                        .setAccountNumber(88888888)
                        .setEmail("88888888@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setFirstName("Rafael")
                        .setLastName("NADAL")
                        .setPhoneNumber("+85288888888")
                        .setBirthdate(LocalDate.of(1989, 3, 20))
                        .setBalance(ACCOUNT_INITIAL_BALANCE);
                accountRepository.save(account2);
            }
        };
    }
}
