package com.example.mission2.web.response;

import com.example.mission2.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DeleteAccountResponse {
    private Long userId;
    private String accountNumber;
    private LocalDateTime unRegisteredTime;

    @Builder
    public DeleteAccountResponse(Long userId, String accountNumber, LocalDateTime unRegisteredTime) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.unRegisteredTime = unRegisteredTime;
    }

    public static DeleteAccountResponse of(Account account) {
        DeleteAccountResponse response = DeleteAccountResponse.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .unRegisteredTime(LocalDateTime.now())
                .build();

        return response;
    }
}
