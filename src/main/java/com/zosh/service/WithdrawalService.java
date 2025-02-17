package com.zosh.service;

import com.zosh.modal.User;
import com.zosh.modal.Withdrawal;

import java.util.List;

public interface WithdrawalService {


    Withdrawal requestWithdrawal(Long amount, User user);


    Withdrawal procedWithWithdrawal(Long withdrawalId,boolean accept) throws Exception;

    List<Withdrawal> getUserWithdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();

}
