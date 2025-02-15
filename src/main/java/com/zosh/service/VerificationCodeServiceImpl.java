package com.zosh.service;

import com.zosh.domain.VerificationType;
import com.zosh.modal.User;
import com.zosh.modal.VerificationCode;
import com.zosh.repository.VerificationCodeRepository;
import com.zosh.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {


    @Autowired
 private VerificationCodeRepository verificationCodeRepository;


    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
        VerificationCode verificationCode1 = new VerificationCode();

        verificationCode1.setOtp(OtpUtils.generateOTP());
        verificationCode1.setVerificationType(verificationType);

verificationCode1.setUser(user);

        return verificationCodeRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCode = verificationCodeRepository.findById(id);

     if(verificationCode.isPresent()){
         return verificationCode.get();
     }

        throw new Exception("VERIFICATION CODE NOT FOUND!!");
    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long user) {



        return verificationCodeRepository.findByUserId(user);
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {
verificationCodeRepository.delete(verificationCode);
    }
}
