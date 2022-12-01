package com.example.mission2.web.response;

import com.example.mission2.domain.account.Account;
import com.example.mission2.domain.transaction.Transaction;
import com.example.mission2.type.TransactionResultType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UseTransactionResponse {
    private String accountNumber;
    private TransactionResultType transactionResultType;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactedAt;

    public static UseTransactionResponse of(Transaction transaction) {
       return  UseTransactionResponse.builder()
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transactionResultType(transaction.getTransactionResultType())
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .transactedAt(transaction.getTransactedAt())
                .build();
    }
}
