package com.zosh.service;


import com.zosh.domain.WalletTransactionType;
import com.zosh.modal.Wallet.Wallet;
import com.zosh.modal.WalletTransaction;

import java.util.List;

public interface WalletTransactionService {
    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        String transferId,
                                        String purpose,
                                        Long amount
    );

    List<WalletTransaction> getTransactions(Wallet wallet, WalletTransactionType type);
    List<WalletTransaction> getTransactionsByWallet(Wallet wallet);
}
