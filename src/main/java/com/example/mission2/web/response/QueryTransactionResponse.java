package com.example.mission2.web.response;

import com.example.mission2.domain.transaction.Transaction;
import com.example.mission2.type.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTransactionResponse {
    private String accountNumber;
    private TransactionType transactionType;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactedAt;

    public static QueryTransactionResponse of(Transaction transaction) {
        return QueryTransactionResponse.builder()
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .transactedAt(transaction.getTransactedAt())
                .build();
    }
}
