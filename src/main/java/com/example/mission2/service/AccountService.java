package com.example.mission2.service;

import com.example.mission2.domain.account.Account;
import com.example.mission2.domain.account.AccountRepository;
import com.example.mission2.exception.Mission2Exception;
import com.example.mission2.type.AccountStatus;
import com.example.mission2.domain.accountuser.AccountUser;
import com.example.mission2.domain.accountuser.AccountUserRepository;
import com.example.mission2.web.request.CreateAccountRequest;
import com.example.mission2.web.request.DeleteAccountRequest;
import com.example.mission2.web.response.CreateAccountResponse;
import com.example.mission2.web.response.DeleteAccountResponse;
import com.example.mission2.web.response.GetAccountListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.mission2.type.ErrorCode.*;
import static com.example.mission2.type.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        Long userId = createAccountRequest.getUserId();
        Long initialBalance = createAccountRequest.getInitialBalance();

        AccountUser accountUser = getAccountUser(userId);

        validateCreateAccount(accountUser);

        String accountNumber = createAccountNums();
        Account account = Account.of(accountUser, accountNumber,
                AccountStatus.IN_USE, initialBalance, LocalDateTime.now());
        Account created = accountRepository.save(account);

        return CreateAccountResponse.of(created);
    }

    public DeleteAccountResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        Long userId = deleteAccountRequest.getUserId();
        String accountNumber = deleteAccountRequest.getAccountNumber();

        AccountUser accountUser = getAccountUser(userId);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new Mission2Exception(ACCOUNT_NOT_FOUND));

        validateDeleteAccount(accountUser, account);

        account.setAccountStatus(AccountStatus.UNREGISTERED);
        account.setUnRegisteredAt(LocalDateTime.now());

        accountRepository.save(account);

        return DeleteAccountResponse.of(account);
    }

    public List<GetAccountListResponse> getAccountList(Long userId) {
        AccountUser accountUser = getAccountUser(userId);

        List<Account> accountList = accountRepository.findAllByAccountUser(accountUser);
        return accountList.stream()
                .map(account -> GetAccountListResponse.of(account))
                .collect(Collectors.toList());
    }

    private AccountUser getAccountUser(Long userId) {
        return accountUserRepository.findById(userId)
                .orElseThrow(() -> new Mission2Exception(USER_NOT_FOUND));
    }

    private void validateCreateAccount(AccountUser accountUser) {
        List<Account> accountByUser = accountRepository.findAllByAccountUser(accountUser);
        if (accountByUser.size() >= 10) {
            throw new Mission2Exception(MAX_ACCOUNT_PER_USER_10);
        }
    }

    private String createAccountNums() {
        Random random = new Random();
        String accountNums = "";
        do {
            for (int i = 0; i < 10; i++) {
                accountNums += String.valueOf(random.nextInt(9));
            }

            System.out.println(accountNums);
        } while (accountRepository.existsAccountByAccountNumber(accountNums));

        return accountNums;
    }

    private static void validateDeleteAccount(AccountUser accountUser, Account account) {
        if (!Objects.equals(account.getAccountUser().getId(), accountUser.getId())) { // 요청한 사용자와 계좌번호의 소유주가 다른 경우
            throw new Mission2Exception(USER_ACCOUNT_UN_MATCH);
        }

        if (account.getAccountStatus() == AccountStatus.UNREGISTERED) { // 계좌가 이미 해지된 경우
            throw new Mission2Exception(ACCOUNT_ALREADY_UNREGISTERED);
        }

        if (account.getBalance() > 0) { // 계좌에 잔액이 남아있는 경우
            throw new Mission2Exception(ACCOUNT_BALANCE_NOT_EMPTY);
        }
    }
}
