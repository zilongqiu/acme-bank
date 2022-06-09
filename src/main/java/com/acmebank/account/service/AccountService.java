package com.acmebank.account.service;

import com.acmebank.account.dto.mapper.AccountMapper;
import com.acmebank.account.dto.model.AccountDto;
import com.acmebank.account.exception.CustomException;
import com.acmebank.account.exception.EntityType;
import com.acmebank.account.exception.ExceptionType;
import com.acmebank.account.model.Account;
import com.acmebank.account.repository.AccountRepository;
import com.acmebank.account.request.AccountTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.acmebank.account.exception.EntityType.ACCOUNT;
import static com.acmebank.account.exception.ExceptionType.ENTITY_BALANCE_INVALID;
import static com.acmebank.account.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto getAccount(Integer accountNumber) {
        return AccountMapper.toAccountDto(getAccountByAccountNumber(accountNumber));
    }

    @Transactional
    public AccountDto transfer(AccountTransferRequest accountTransferRequest) {
        Account accountFrom = getAccountByAccountNumber(accountTransferRequest.getAccountFrom());
        Long amount = accountTransferRequest.getAmount();

        if (accountFrom.getBalance() < amount || amount <= 0) {
            throw exception(ACCOUNT, ENTITY_BALANCE_INVALID, amount.toString(), accountTransferRequest.getAccountFrom().toString());
        }

        Account accountTo = getAccountByAccountNumber(accountTransferRequest.getAccountTo());

        // Transfer money from accountFrom to accountTo
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);

        return AccountMapper.toAccountDto(accountFrom);
    }

    private Account getAccountByAccountNumber(Integer accountNumber) {
        Optional<Account> account = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber));

        if (!account.isPresent()) {
            throw exception(ACCOUNT, ENTITY_NOT_FOUND, accountNumber.toString());
        }

        return account.get();
    }

    /**
     * Returns a new RuntimeException
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return CustomException.throwException(entityType, exceptionType, args);
    }
}
