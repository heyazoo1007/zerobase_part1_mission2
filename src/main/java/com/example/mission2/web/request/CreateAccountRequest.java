package com.example.mission2.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private Long userId;
    private Long initialBalance;
}
