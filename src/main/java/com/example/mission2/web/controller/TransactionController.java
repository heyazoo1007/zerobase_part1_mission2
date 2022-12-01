package com.example.mission2.web.controller;

import com.example.mission2.service.transaction.TransactionService;
import com.example.mission2.web.request.CancelBalanceRequest;
import com.example.mission2.web.request.UseBalanceRequest;
import com.example.mission2.web.response.CancelBalanceResponse;
import com.example.mission2.web.response.QueryTransactionResponse;
import com.example.mission2.web.response.UseBalanceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/use")
    public UseBalanceResponse useTransaction(
            @RequestBody @Valid UseBalanceRequest request) {
        return transactionService.useBalance(request);
    }

    @PostMapping("/cancel")
    public CancelBalanceResponse cancelTransaction(
            @RequestBody @Valid CancelBalanceRequest request) {
        return transactionService.cancelBalance(request);
    }

    @GetMapping("/{transactionId}")
    public QueryTransactionResponse getTransaction(
            @PathVariable String transactionId) {
        return transactionService.queryTransaction(transactionId);
    }
}
