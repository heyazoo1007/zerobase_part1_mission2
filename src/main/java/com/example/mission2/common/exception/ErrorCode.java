package com.example.mission2.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.mission2.common.exception.ErrorStatusCode.*;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // 404 Not Found
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않습니다.");

    private final ErrorStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
