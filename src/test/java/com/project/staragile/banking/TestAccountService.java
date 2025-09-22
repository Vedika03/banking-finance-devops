package com.project.staragile.banking;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;

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
        account.setAccountName("Vedika");
        account.setAccountType("SAVINGS");
        account.setBalance(5000.0);

        Account saved = accountService.registerAccount(account);
        assertNotNull(saved.getAccountNumber());
        assertEquals(saved.getAccountName(), "Vedika");
    }

    @Test
    public void testUpdateAccount() {
        Account account = accountService.getAccountDetails(1010101010);
        account.setBalance(7000.0);
        Account updated = accountService.updateAccount(account.getAccountNumber(), account);
        assertEquals(updated.getBalance(), 7000.0);
    }

    @Test
    public void testDeleteAccount() {
        accountService.deleteAccount(1010101010);
        assertThrows(RuntimeException.class, () -> accountService.getAccountDetails(1010101010));
    }
}
