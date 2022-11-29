package com.example.mission2.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    @PostMapping("/use")
    public void useTransaction() {

    }

    @PostMapping("/cancel")
    public void cancelTransaction() {

    }

    @GetMapping("/{transactionId}")
    public void getTransaction(@PathVariable Long transactionId) {

    }
}
