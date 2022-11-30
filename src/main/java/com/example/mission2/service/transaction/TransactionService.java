package com.example.mission2.service.transaction;

import com.example.mission2.domain.account.Account;
import com.example.mission2.domain.account.AccountRepository;
import com.example.mission2.domain.accountuser.AccountUser;
import com.example.mission2.domain.accountuser.AccountUserRepository;
import com.example.mission2.domain.transaction.Transaction;
import com.example.mission2.domain.transaction.TransactionRepository;
import com.example.mission2.exception.Mission2Exception;
import com.example.mission2.type.AccountStatus;
import com.example.mission2.web.request.UseTransactionRequest;
import com.example.mission2.web.response.UseTransactionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.example.mission2.type.ErrorCode.*;
import static com.example.mission2.type.TransactionResultType.SUCCESS;
import static com.example.mission2.type.TransactionType.USE;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    @Transactional
    public UseTransactionResponse useTransaction(UseTransactionRequest request) {
        Long userId = request.getUserId();
        String accountNumber = request.getAccountNumber();
        Long amount = request.getAmount();

        AccountUser accountUser = getAccountUser(userId);

        Account account = getAccount(accountNumber);

        validateUseBalance(amount, accountUser, account);

        account.useBalance(amount);

        return UseTransactionResponse.of(transactionRepository.save(Transaction.builder()
                .account(account)
                .transactionType(USE)
                .transactionResultType(SUCCESS)
                .amount(amount)
                .balanceSnapshot(account.getBalance())
                .transactionId(UUID.randomUUID().toString().replace("-", ""))
                .transactedAt(LocalDateTime.now())
                .build()));
    }

    private Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new Mission2Exception(ACCOUNT_NOT_FOUND));
    }

    private AccountUser getAccountUser(Long userId) {
        return accountUserRepository.findById(userId)
                .orElseThrow(() -> new Mission2Exception(USER_NOT_FOUND));
    }

    @Transactional
    private static void validateUseBalance(Long amount, AccountUser accountUser, Account account) {
        if (!Objects.equals(accountUser.getId(), account.getAccountUser().getId())) {
            throw new Mission2Exception(USER_ACCOUNT_UN_MATCH);
        }
        if (account.getAccountStatus() != AccountStatus.IN_USE) {
            throw new Mission2Exception(ACCOUNT_ALREADY_UNREGISTERED);
        }
        if (account.getBalance() < amount) {
            throw new Mission2Exception(AMOUNT_EXCEED_BALANCE);
        }
    }
}
