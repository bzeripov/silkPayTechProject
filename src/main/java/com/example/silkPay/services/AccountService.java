package com.example.silkPay.services;


import com.example.silkPay.entities.Account;

public interface AccountService {
    Account newAccount(Integer balance);

    Integer getBalance(Long id);

    void transfer(String senderAccount, String account, Integer amount);
}
