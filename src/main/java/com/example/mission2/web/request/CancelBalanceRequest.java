package com.example.mission2.web.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelBalanceRequest {
    private String transactionId;
    private String accountNumber;
    private Long amount;
}
