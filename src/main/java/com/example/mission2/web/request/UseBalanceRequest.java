package com.example.mission2.web.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UseBalanceRequest {
    private Long userId;
    private String accountNumber;
    private Long amount;
}
