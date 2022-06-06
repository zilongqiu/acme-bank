package com.acmebank.account.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "account",
        indexes = @Index(
                name = "idx_account_number",
                columnList = "number",
                unique = true
        ))
@Data
@Accessors(chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Account Number (ex: 12345678, 88888888,...)
    private long number;

    // Balance of the account in cents (ex: 10000 is equivalent of 100HKD)
    private long balance;
}
