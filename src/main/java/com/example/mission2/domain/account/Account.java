package com.example.mission2.domain.account;


import com.example.mission2.domain.accountuser.AccountUser;
import com.example.mission2.exception.Mission2Exception;
import com.example.mission2.type.AccountStatus;
import com.example.mission2.type.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.example.mission2.type.ErrorCode.AMOUNT_EXCEED_BALANCE;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountUser accountUser;

    private String accountNumber;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    private Long balance;

    private LocalDateTime registeredAt;

    private LocalDateTime unRegisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Account of(AccountUser accountUser, String accountNumber, AccountStatus accountStatus, Long balance, LocalDateTime registeredAt) {
        return Account.builder()
                .accountUser(accountUser)
                .accountNumber(accountNumber)
                .accountStatus(accountStatus)
                .balance(balance)
                .registeredAt(registeredAt)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void useBalance(Long amount) {
        if (amount > balance) {
            throw new Mission2Exception(AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void cancelBalance(Long amount) {
        if (amount < 0) {
            throw new Mission2Exception(ErrorCode.INVALID_REQUEST);
        }
        balance -= amount;
    }
}
