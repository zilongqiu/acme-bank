package com.acmebank.account.service;

import com.acmebank.account.dto.model.AccountDto;

public interface AccountServiceInterface {
    AccountDto getAccount(Integer accountNumber);
}
