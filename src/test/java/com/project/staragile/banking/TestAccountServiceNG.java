package com.project.staragile.banking;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAccountServiceNG {

    @Autowired
    AccountService accountService;

    @Test
    public void testAccountRegistration() {
        Account dummy = accountService.registerDummyAccount();
        Assert.assertNotNull(dummy.getAccountNumber());
        Assert.assertEquals(dummy.getAccountName(), "Shubham");
    }
}
