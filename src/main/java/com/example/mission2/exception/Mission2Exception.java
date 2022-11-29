package com.example.mission2.exception;

import com.example.mission2.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mission2Exception extends RuntimeException {
    private ErrorCode errorCode;

    private String errorMessage;

    public Mission2Exception(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
