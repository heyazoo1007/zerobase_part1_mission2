package com.example.mission2.domain.account;

import com.example.mission2.domain.accountuser.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAllByAccountUser(AccountUser accountUser);

    boolean existsAccountByAccountNumber(String accountNumber);
}
