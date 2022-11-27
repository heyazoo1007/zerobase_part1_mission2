package com.example.mission2.web.response;

import com.example.mission2.web.request.CreateAccountRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAccountResponse {
    private String userId;
    private String accountNums;
    private LocalDateTime createdAt;

    @Builder
    public CreateAccountResponse(String userId, String accountNums, LocalDateTime createdAt) {
        this.userId = userId;
        this.accountNums = accountNums;
        this.createdAt = createdAt;
    }

    public static CreateAccountResponse of(CreateAccountRequest createAccountRequest,
                                    String accountNums) {

        return CreateAccountResponse.builder()
                .userId(createAccountRequest.getUserId())
                .accountNums(accountNums)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
