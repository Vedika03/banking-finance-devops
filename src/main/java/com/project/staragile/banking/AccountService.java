package com.project.staragile.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account registerAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountDetails(int accountNo) {
        return accountRepository.findById(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account updateAccount(int accountNo, Account account) {
        Account existing = getAccountDetails(accountNo);
        existing.setAccountName(account.getAccountName());
        existing.setAccountType(account.getAccountType());
        existing.setBalance(account.getBalance());
        return accountRepository.save(existing);
    }

    public void deleteAccount(int accountNo) {
        accountRepository.deleteById(accountNo);
    }
}
