package com.example.mission2.web.controller;

import com.example.mission2.service.transaction.TransactionService;
import com.example.mission2.web.request.UseTransactionRequest;
import com.example.mission2.web.response.UseTransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/use")
    public ResponseEntity<UseTransactionResponse> useTransaction(
            @RequestBody @Valid UseTransactionRequest request) {
        return ResponseEntity
                .status(200)
                .body(transactionService.useTransaction(request));
    }

    @PostMapping("/cancel")
    public void cancelTransaction() {

    }

    @GetMapping("/{transactionId}")
    public void getTransaction(@PathVariable Long transactionId) {

    }
}
