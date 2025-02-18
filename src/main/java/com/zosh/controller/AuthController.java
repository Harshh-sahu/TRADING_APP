package com.zosh.controller;

import com.zosh.config.JwtProvider;
import com.zosh.modal.TwoFactorOTP;
import com.zosh.response.AuthResponse;
import com.zosh.service.CustomUserDetailsService;
import com.zosh.service.EmailService;
import com.zosh.service.TwoFactorOtpService;
import com.zosh.service.WatchListService;
import com.zosh.utils.OtpUtils;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.zosh.modal.User;
import com.zosh.repository.UserRepository;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private WatchListService watchListService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody  User user) throws Exception {



        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if(isEmailExist!= null){
            throw new Exception("Email already exist pr Used with another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());


        User savedUser = userRepository.save(newUser);

   watchListService.createWatchList(savedUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register Success");
return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody  User user) throws Exception {




String userName = user.getEmail();
String Password = user.getPassword();



        Authentication auth =authenticate(userName,Password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);
User authuser = userRepository.findByEmail(userName);
        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two Factor Authentication is enabled");
            res.setTwoFactorAuthEnaled(true);
            String otp = OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP  = twoFactorOtpService.findByUser(authuser.getId());
            if(oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOtp = twoFactorOtpService.createTwoFactorOtp(authuser,otp,jwt);

    emailService.sendVerificationOtpEmail(userName,otp);
            res.setSession(newTwoFactorOtp.getId());
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        }
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login Success");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {


        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");

        }
        if(!password.equals(userDetails.getPassword())){
   throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());


    }






@PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySignInOtp(@PathVariable String otp,@RequestParam String id) throws Exception{

        TwoFactorOTP twoFactorOTP= twoFactorOtpService.findById(id);

        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two Factor Authentication Verified");
            res.setJwt(twoFactorOTP.getJwt());
            res.setTwoFactorAuthEnaled(true);

            return new ResponseEntity<>(res, HttpStatus.CREATED);

        }
        throw new Exception("Invalid OTP");


    }




}
