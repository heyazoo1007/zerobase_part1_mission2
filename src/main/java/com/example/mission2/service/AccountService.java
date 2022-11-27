package com.example.mission2.service;

import com.example.mission2.domain.Account;
import com.example.mission2.domain.AccountRepository;
import com.example.mission2.web.request.CreateAccountRequest;
import com.example.mission2.web.response.CreateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        String userId = createAccountRequest.getUserId();
        Integer initMoney = createAccountRequest.getInitMoney();

        String accountNums = createAccountNums();
        CreateAccountResponse createAccountResponse
                = CreateAccountResponse.of(createAccountRequest, accountNums);

        return createAccountResponse;
    }

    private String createAccountNums() {
        Random random = new Random();
        String accountNums = "";
        Account findAccount = null;
        boolean flag = true;
        do {
            for (int i = 0; i < 10; i++) {
                accountNums += String.valueOf(random.nextInt(9));
            }

            // 중복된 계좌번호 있는지 확인
            findAccount = accountRepository.findByAccountNums(accountNums);
            if (findAccount != null) {
                flag = false;
            }
        } while (!flag);
        return accountNums;
    }
}
