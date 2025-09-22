package com.project.staragile.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Create / Register new account
    public Account registerAccount(Account account) {
        if (accountRepository.existsById(account.getAccountNo())) {
            throw new RuntimeException("Account already exists with accountNo: " + account.getAccountNo());
        }
        return accountRepository.save(account);
    }

    // Update existing account
    public Account updateAccount(String accountNo, Account updatedAccount) {
        Optional<Account> optional = accountRepository.findById(accountNo);
        if (!optional.isPresent()) {
            throw new RuntimeException("Account not found with accountNo: " + accountNo);
        }
        Account existing = optional.get();
        existing.setAccountName(updatedAccount.getAccountName());
        existing.setPolicyType(updatedAccount.getPolicyType());
        existing.setSumInsured(updatedAccount.getSumInsured());
        existing.setStartDate(updatedAccount.getStartDate());
        return accountRepository.save(existing);
    }

    // Get account details
    public Account getAccountDetails(String accountNo) {
        return accountRepository.findById(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found with accountNo: " + accountNo));
    }

    // Delete account
    public void deleteAccount(String accountNo) {
        if (!accountRepository.existsById(accountNo)) {
            throw new RuntimeException("Account not found with accountNo: " + accountNo);
        }
        accountRepository.deleteById(accountNo);
    }
}
