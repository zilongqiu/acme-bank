package com.acmebank.account.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class AccountTransferRequest {
    @NotNull(message = "{constraints.NotNull.message}")
    private Integer accountFrom;

    @NotNull(message = "{constraints.NotNull.message}")
    private Integer accountTo;

    @NotNull(message = "{constraints.NotNull.message}")
    private Long amount;
}
