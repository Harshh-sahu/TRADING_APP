package com.zosh.service;

import com.zosh.domain.VerificationType;
import com.zosh.modal.ForgetPasswordToken;
import com.zosh.modal.User;
import com.zosh.repository.ForgetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Override
    public ForgetPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo) {

        ForgetPasswordToken token = new ForgetPasswordToken();



        return null;
    }

    @Override
    public ForgetPasswordToken findById(String id) {
        return null;
    }

    @Override
    public ForgetPasswordToken findByUser(Long UserId) {
        return null;
    }

    @Override
    public void deleteToken(ForgetPasswordToken token) {

    }
}
