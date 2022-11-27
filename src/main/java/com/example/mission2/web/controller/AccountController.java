package com.example.mission2.web.controller;

import com.example.mission2.service.AccountService;
import com.example.mission2.web.request.CreateAccountRequest;
import com.example.mission2.web.response.CreateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        CreateAccountResponse createAccountResponse
                = accountService.createAccount(createAccountRequest);

        return ResponseEntity.status(200)
                .body(createAccountResponse);
    }
}
