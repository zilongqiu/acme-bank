package com.acmebank.account.dto.mapper;

import com.acmebank.account.dto.model.AccountDto;
import com.acmebank.account.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public static AccountDto toAccountDto(Account account) {
        return new AccountDto()
                .setEmail(account.getEmail())
                .setFirstName(account.getFirstName())
                .setLastName(account.getLastName())
                .setPhoneNumber(account.getPhoneNumber())
                .setBirthDate(account.getBirthdate())
                .setAccountNumber(account.getAccountNumber())
                .setBalance(account.getBalance());
    }
}
