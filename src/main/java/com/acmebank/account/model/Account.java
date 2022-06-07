package com.acmebank.account.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "account",
        indexes = @Index(
                name = "idx_account_number",
                columnList = "accountNumber",
                unique = true
        ))
@Data
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    private LocalDate birthdate;

    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    private int accountNumber;

    // Balance of the account in cents (ex: 10000 is equivalent of 100HKD)
    @NotNull
    @Column(nullable = false)
    private long balance = 0;
}
