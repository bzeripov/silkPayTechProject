package com.example.silkPay.services.impl;

import com.example.silkPay.entities.Account;
import com.example.silkPay.repositories.AccountRepository;
import com.example.silkPay.services.AccountService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {
    static int acc = 1000000;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account newAccount(Integer balance) {
        Account account = new Account();
        account.setBalance(balance);
        account.setAccount(String.valueOf(++acc));
        accountRepository.save(account);
        return account;
    }

    @Override
    public Integer getBalance(Long id) {
        if(!accountRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id");
        return accountRepository.getReferenceById(id).getBalance();
    }

    @Override
    public void transfer(String senderAccount, String account, Integer amount) {
        if(!accountRepository.existsByAccount(senderAccount))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no an account by given id");
        if(!accountRepository.existsByAccount(account))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no an account by given account");
        Account sender = accountRepository.getByAccount(senderAccount);
        Account recipient = accountRepository.getByAccount(account);
        if(sender.getBalance() < amount || sender == recipient)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance is not enough");
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
        accountRepository.saveAll(Arrays.asList(sender, recipient));
    }

}
