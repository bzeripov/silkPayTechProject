package com.example.silkPay.repositories;

import com.example.silkPay.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccount(String account);
    Account getByAccount(String account);
}
