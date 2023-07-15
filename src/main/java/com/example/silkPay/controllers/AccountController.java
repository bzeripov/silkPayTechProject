package com.example.silkPay.controllers;

import com.example.silkPay.entities.Account;
import com.example.silkPay.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public void newAccount(@RequestParam(name = "balance", defaultValue = "0")Integer balance){
        accountService.newAccount(balance);
    }

    @GetMapping("/{id}")
    public Integer viewBalance(@PathVariable("id") Long id){
        return accountService.getBalance(id);
    }

    @PatchMapping("/transfer")
    public void transfer(@RequestParam("senderAccount")String senderAccount,
                         @RequestParam("account")String account,
                         @RequestParam("amount")Integer amount){
        accountService.transfer(senderAccount, account, amount);
    }
}
