package com.acmebank.controller.v1;

import com.acmebank.account.model.Account;
import com.acmebank.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    private Account accountMock1;

    private Account accountMock2;

    @BeforeEach
    void setUp() {
        accountMock1 = new Account();
        accountMock1.setAccountNumber(12345678)
                .setFirstName("TestFirstName")
                .setLastName("TestLastName")
                .setPhoneNumber("+85212121212")
                .setEmail("test@test.com")
                .setBalance(1_000_000);

        accountMock2 = new Account();
        accountMock2.setAccountNumber(87654321)
                .setFirstName("TestFirstName2")
                .setLastName("TestLastName2")
                .setPhoneNumber("+85234343434")
                .setEmail("test2@test.com")
                .setBalance(1_000_000);
    }

    @Test
    public void getAccountSuccess() throws Exception {
        when(accountRepository.findByAccountNumber(12345678))
            .thenReturn(accountMock1);

        this.mockMvc.perform(get("/api/v1/account/12345678")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("OK")))
            .andExpect(jsonPath("$.errors", nullValue()))
            .andExpect(jsonPath("$.data.accountNumber", is(accountMock1.getAccountNumber())))
            .andExpect(jsonPath("$.data.firstName", is(accountMock1.getFirstName())))
            .andExpect(jsonPath("$.data.lastName", is(accountMock1.getLastName())))
            .andExpect(jsonPath("$.data.phoneNumber", is(accountMock1.getPhoneNumber())))
            .andExpect(jsonPath("$.data.balance", is((int)accountMock1.getBalance())))
            .andExpect(jsonPath("$.data.birthDate", is(accountMock1.getBirthdate())));
    }

    @Test
    public void getAccountNotFoundException() throws Exception {
        when(accountRepository.findByAccountNumber(87654321))
            .thenReturn(null);

        this.mockMvc.perform(get("/api/v1/account/87654321")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", is("NOT_FOUND")))
            .andExpect(jsonPath("$.data", nullValue()))
            .andExpect(jsonPath("$.errors.message", is("Requested account 87654321 does not exist.")))
            .andExpect(jsonPath("$.errors.details", is("Requested account 87654321 does not exist.")));
    }

    @Test
    public void accountTransferSuccess() throws Exception {
        when(accountRepository.findByAccountNumber(12345678))
            .thenReturn(accountMock1);

        when(accountRepository.findByAccountNumber(87654321))
            .thenReturn(accountMock2);

        this.mockMvc.perform(post("/api/v1/account/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"accountFrom\":12345678,\"accountTo\":87654321,\"amount\":10000}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("OK")))
            .andExpect(jsonPath("$.errors", nullValue()))
            .andExpect(jsonPath("$.data.accountNumber", is(accountMock1.getAccountNumber())))
            .andExpect(jsonPath("$.data.firstName", is(accountMock1.getFirstName())))
            .andExpect(jsonPath("$.data.lastName", is(accountMock1.getLastName())))
            .andExpect(jsonPath("$.data.phoneNumber", is(accountMock1.getPhoneNumber())))
            .andExpect(jsonPath("$.data.balance", is(990000)))
            .andExpect(jsonPath("$.data.birthDate", is(accountMock1.getBirthdate())));
    }

    @Test
    public void accountTransferNotFoundException() throws Exception {
        when(accountRepository.findByAccountNumber(anyInt()))
                .thenReturn(null);

        this.mockMvc.perform(post("/api/v1/account/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountFrom\":12345678,\"accountTo\":87654321,\"amount\":10000}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.data", nullValue()))
                .andExpect(jsonPath("$.errors.message", is("Requested account 12345678 does not exist.")))
                .andExpect(jsonPath("$.errors.details", is("Requested account 12345678 does not exist.")));
    }

    @Test
    public void accountTransferInvalidBalanceException() throws Exception {
        accountMock1.setBalance(9_000);

        when(accountRepository.findByAccountNumber(12345678))
                .thenReturn(accountMock1);

        when(accountRepository.findByAccountNumber(87654321))
                .thenReturn(accountMock2);

        this.mockMvc.perform(post("/api/v1/account/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountFrom\":12345678,\"accountTo\":87654321,\"amount\":10000}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.data", nullValue()))
                .andExpect(jsonPath("$.errors.message", is("Invalid transfer of HKD10000 from account 12345678.")))
                .andExpect(jsonPath("$.errors.details", is("Invalid transfer of HKD10000 from account 12345678.")));
    }
}
