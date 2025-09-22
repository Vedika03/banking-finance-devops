package com.project.staragile.banking;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.expectThrows;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest
public class TestAccountService {

    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setAccountNo("ACC2001");
        account.setAccountName("Vedika");
        account.setPolicyType("TERM");
        account.setSumInsured(500000.0);
        account.setStartDate(LocalDate.now());

        Account saved = accountService.registerAccount(account);
        assertNotNull(saved.getAccountNo(), "Account No should not be null");
        assertEquals(saved.getAccountName(), "Vedika");
        assertEquals(saved.getPolicyType(), "TERM");
    }

    @Test
    public void testUpdateAccount() {
        // Preload account for test
        Account account = accountService.getAccountDetails("ACC2001");
        account.setSumInsured(700000.0);
        Account updated = accountService.updateAccount(account.getAccountNo(), account);
        assertEquals(updated.getSumInsured(), 700000.0);
    }

    @Test
    public void testDeleteAccount() {
        accountService.deleteAccount("ACC2001");
        expectThrows(RuntimeException.class, () -> accountService.getAccountDetails("ACC2001"));
    }
}
