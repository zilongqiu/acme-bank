package com.acmebank.account.controller.v1;

import com.acmebank.account.dto.response.Response;
import com.acmebank.account.request.AccountTransferRequest;
import com.acmebank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "{accountNumber}")
    public Response getAccount(@PathVariable("accountNumber") Integer accountNumber) {
        return Response.ok().setData(accountService.getAccount(accountNumber));
    }

    @PostMapping("transfer")
    public Response transfer(@RequestBody @Valid AccountTransferRequest accountTransferRequest) {
        return Response.ok().setData(accountService.transfer(accountTransferRequest));
    }
}
