package com.example.mission2.web.controller;

import com.example.mission2.service.AccountService;
import com.example.mission2.web.request.CreateAccountRequest;
import com.example.mission2.web.request.DeleteAccountRequest;
import com.example.mission2.web.response.CreateAccountResponse;
import com.example.mission2.web.response.DeleteAccountResponse;
import com.example.mission2.web.response.GetAccountListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("")
    public ResponseEntity createAccount(
            @RequestBody CreateAccountRequest createAccountRequest) {
        CreateAccountResponse createAccountResponse =
                accountService.createAccount(createAccountRequest);

        return ResponseEntity.status(200)
                .body(createAccountResponse);
    }

    @DeleteMapping("")
    public ResponseEntity deleteAccount(
            @RequestBody DeleteAccountRequest deleteAccountRequest) {
        DeleteAccountResponse deleteAccountResponse =
                accountService.deleteAccount(deleteAccountRequest);

        return ResponseEntity.status(200)
                .body(deleteAccountResponse);
    }

    @GetMapping("/{userId}")
    public List<GetAccountListResponse> getAccountByUserId(
            @PathVariable("userId") Long userId) {
        List<GetAccountListResponse> getAccountListResponse
                = accountService.getAccountList(userId);

        return getAccountListResponse;
    }
}
