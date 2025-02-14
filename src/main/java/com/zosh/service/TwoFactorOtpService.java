package com.zosh.service;

import com.zosh.modal.TwoFactorOTP;
import com.zosh.modal.User;
import org.springframework.stereotype.Service;

public interface TwoFactorOtpService {


    TwoFactorOTP createTwoFactorOtp(User user,String otp,String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP,String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);


}
