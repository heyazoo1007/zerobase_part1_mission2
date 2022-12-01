package com.example.mission2.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("올바르지 않은 요청입니다."),
    USER_NOT_FOUND("사용자가 없습니다."),
    ACCOUNT_NOT_FOUND("계좌가 없습니다."),
    MAX_ACCOUNT_PER_USER_10("최대 계좌는 10개 이하입니다."),
    USER_ACCOUNT_UN_MATCH("사용자와 계좌 소유주가 다릅니다."),
    ACCOUNT_ALREADY_UNREGISTERED("이미 해지된 계좌입니다."),
    ACCOUNT_BALANCE_NOT_EMPTY("잔액이 있는 계좌는 해지 할 수 없습니다."),
    AMOUNT_EXCEED_BALANCE("거래 금액이 계좌 잔액보다 큽니다."),
    ACCOUNT_TRANSACTION_UN_MATCH("거래에 해당하는 계좌가 없습니다."),
    TRANSACTION_NOT_FOUND("거래 아이디에 해당하는 거래가 없습니다."),
    CANCEL_MUST_FULLY("부분 취소는 허용되지 않습니다."),
    TOO_OLD_ORDER_TO_CANCEL("1년 이내 거래만 취소 가능합니다.");


    private final String description;
}
