package com.zosh.service;

import com.zosh.domain.VerificationType;
import com.zosh.modal.ForgetPasswordToken;
import com.zosh.modal.User;

public interface ForgetPasswordService {

    ForgetPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,String sendTo);

    ForgetPasswordToken findById(String id);

    ForgetPasswordToken findByUser(Long UserId);


    void deleteToken(ForgetPasswordToken token);








}
