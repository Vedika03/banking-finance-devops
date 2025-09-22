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
        account.
