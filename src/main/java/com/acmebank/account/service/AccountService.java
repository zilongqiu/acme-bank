package com.acmebank.account.service;

import com.acmebank.account.dto.mapper.AccountMapper;
import com.acmebank.account.dto.model.AccountDto;
import com.acmebank.account.exception.CustomException;
import com.acmebank.account.exception.EntityType;
import com.acmebank.account.exception.ExceptionType;
import com.acmebank.account.model.Account;
import com.acmebank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.acmebank.account.exception.EntityType.ACCOUNT;
import static com.acmebank.account.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto getAccount(Integer accountNumber) {
        Optional<Account> account = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber));

        if (!account.isPresent()) {
            throw exception(ACCOUNT, ENTITY_NOT_FOUND);
        }

        Account accountModel = account.get();
        accountModel
                .setEmail(accountModel.getEmail())
                .setFirstName(accountModel.getFirstName())
                .setLastName(accountModel.getLastName())
                .setPhoneNumber(accountModel.getPhoneNumber())
                .setBirthdate(accountModel.getBirthdate())
                .setAccountNumber(accountModel.getAccountNumber())
                .setBalance(accountModel.getBalance());

        return AccountMapper.toAccountDto(accountModel);
    }

    /**
     * Returns a new RuntimeException
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return CustomException.throwException(entityType, exceptionType, args);
    }
}
