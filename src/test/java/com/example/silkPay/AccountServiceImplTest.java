package com.example.silkPay;


import com.example.silkPay.entities.Account;
import com.example.silkPay.repositories.AccountRepository;
import com.example.silkPay.services.AccountService;
import com.example.silkPay.services.impl.AccountServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private Account account1;
    private Account account2;

    @Before
    public void initAndInsert(){
        account1 = accountService.newAccount(100000);
        account2 = accountService.newAccount(200000);
    }

    @Test
    public void transferTest(){
        accountService.transfer(account1.getAccount(), account2.getAccount(), 100000);
        account1 = accountRepository.getByAccount(account1.getAccount());
        account2 = accountRepository.getByAccount(account2.getAccount());
        assertEquals( "After the transfer the first account balance has to be 0",
                0, (int)account1.getBalance());
        assertEquals("After the transfer the second account balance has to be 300000",
                300000,(int)account2.getBalance());
    }

    @After
    public void clear(){
        accountRepository.delete(account1);
        accountRepository.delete(account2);
    }

}
