package com.zosh.controller;

import com.zosh.modal.User;
import com.zosh.modal.Wallet.Wallet;
import com.zosh.modal.WalletTransaction;
import com.zosh.service.UserService;
import com.zosh.service.WallerService;
import com.zosh.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionConrtroller {

    @Autowired
    private WallerService wallerService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletTransactionService walletTransactionService;

    @GetMapping("/api/transactions")
    public ResponseEntity<List<WalletTransaction>> getUserWallet(
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserProfileByJwt(jwt);

        Wallet wallet = wallerService.getUserWallet(user);

        List<WalletTransaction> transactionsList = walletTransactionService.getTransactionsByWallet(wallet);

        return  new ResponseEntity<>(transactionsList, HttpStatus.ACCEPTED);
    }




}
