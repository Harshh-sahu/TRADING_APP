package com.zosh.service;

import com.zosh.modal.TwoFactorOTP;
import com.zosh.modal.User;

public class TwoFactorOtpServiceImpl implements TwoFactorOtpService{





    @Override
    public TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt) {
        return null;
    }

    @Override
    public TwoFactorOTP findByUser(Long userId) {
        return null;
    }

    @Override
    public TwoFactorOTP findById(String id) {

        return null;
    }

    @Override
    public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
        return false;
    }

    @Override
    public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP) {

    }
}
