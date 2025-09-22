package com.project.staragile.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create Account
    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account){
        return accountService.registerAccount(account);
    }

    // Update Account
    @PutMapping("/updateAccount/{accountNo}")
    public Account updateAccount(@PathVariable String accountNo, @RequestBody Account account){
        return accountService.updateAccount(accountNo, account);
    }

    // View Policy
    @GetMapping("/viewPolicy/{accountNo}")
    public Account viewPolicy(@PathVariable String accountNo){
        return accountService.getAccountDetails(accountNo);
    }

    // Delete Policy
    @DeleteMapping("/deletePolicy/{accountNo}")
    public String deletePolicy(@PathVariable String accountNo){
        accountService.deleteAccount(accountNo);
        return "Account deleted successfully";
    }
}
	