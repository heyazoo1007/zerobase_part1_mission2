package com.example.mission2.web.response;

import com.example.mission2.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAccountResponse {
    private Long userId;
    private String accountNumber;
    private LocalDateTime createdAt;

    @Builder
    public CreateAccountResponse(Long userId, String accountNumber, LocalDateTime createdAt) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public static CreateAccountResponse of(Account account) {

        return CreateAccountResponse.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
