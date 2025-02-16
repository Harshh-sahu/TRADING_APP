package com.zosh.service;

import com.zosh.modal.Order;
import com.zosh.modal.User;
import com.zosh.modal.Wallet.Wallet;

public interface WallerService {

Wallet getUserWallet(User user);

Wallet addBalance(Wallet  wallet,Long Money);

Wallet findWalletById(Long id) throws Exception;
Wallet walletToWalletTransfer(User sender,Wallet receiverWallet,Long amount) throws Exception;

Wallet payOrderPayment(Order order, User user) throws Exception;








}
