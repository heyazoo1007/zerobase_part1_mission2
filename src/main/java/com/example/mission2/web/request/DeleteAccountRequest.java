package com.example.mission2.web.request;

import lombok.Getter;

@Getter
public class DeleteAccountRequest {
    private Long userId;
    private String accountNumber;
}
