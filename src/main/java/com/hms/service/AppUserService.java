package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LogInDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;
    private JWTService jwtService;
    private OTPService otpService;

    public AppUserService(AppUserRepository appUserRepository, JWTService jwtService, OTPService otpService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
        this.otpService = otpService;
    }

    public String verifyLoginByUserNameAndPassword(LogInDto dto) {
        Optional<AppUser> optUser = appUserRepository.findByUserName(dto.getUserName());

        if(optUser.isPresent()){
            AppUser appUser = optUser.get();

            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
             //generate token
                String token = jwtService.generateToken(appUser.getUserName());
                return token;
            } else {
                // Password mismatch
                return  null;
                //throw new RuntimeException("Invalid password");
            }
        }else {
            return null;
        }
    }
    public String checkMobileAndGenerateAndSendOtp(String mobile) {

        Optional<AppUser> optMobile = appUserRepository.findByMobileNumber(mobile);

        if(optMobile.isPresent()){
            String string = otpService.generateAndSendOtp(mobile);
            return "OTP generated";
        }else{
            return "invalid number provided";
        }
    }

    public String verifyLoginByMobileAndOTP(String mobile, String otp){

        String token = null;

        Optional<AppUser> optMobile = appUserRepository.findByMobileNumber(mobile);
        if(optMobile.isPresent()) {
            AppUser appUser = optMobile.get();
            token = jwtService.generateToken(appUser.getUserName());
        }

        boolean isValid = otpService.validateOTP(mobile, otp);
        if (isValid){
            return token;
        }else {
            return null;
        }

    }

}
