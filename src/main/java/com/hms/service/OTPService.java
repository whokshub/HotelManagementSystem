package com.hms.service;

import com.hms.util.OTPData;
import com.hms.util.OTPUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OTPService {

    private OTPUtil otpUtil;
    private SMSService smsService;

    public OTPService(OTPUtil otpUtil, SMSService smsService) {
        this.otpUtil = otpUtil;
        this.smsService = smsService;
    }

    //Temporary storage for OTPs(in a real app, use a persistent storage
    private final Map<String,OTPData> otpStorage = new HashMap();
    private final static long OTP_EXPIRY_TIME = 5*60*1000;//otp expiry in 5 Minutes


    //generateAndSendOtp
    public String generateAndSendOtp(String mobile){

        //generate OTP
        String otp = otpUtil.generateOTP();

        //store OTP and its expiry time
        otpStorage.put(mobile, new OTPData(otp, System.currentTimeMillis() + OTP_EXPIRY_TIME));

        //send the otp via message
        String mobileNumber = "+91" + mobile;
        smsService.sendSMS(mobileNumber, "Your OTP is : " + otp);

        return otp;
    }

    public boolean validateOTP(String mobile, String otp) {
        OTPData storedOTPData = otpStorage.get(mobile);

        // If OTP data is not found for the mobile number
        if (storedOTPData == null) {
            return false;
        }

        // Check if the OTP has expired
        if (System.currentTimeMillis() > storedOTPData.getExpiryTime()) {
            otpStorage.remove(mobile); // Remove expired OTP data
            return false;
        }

        // Validate the provided OTP
        if (storedOTPData.getOtp().equals(otp)) {
            otpStorage.remove(mobile); // Remove OTP after successful validation
            return true;
        }

        return false; // Invalid OTP
    }

    // Optional: Method to clear expired OTPs (can be run periodically)
//    public void clearExpiredOTPs() {
//        long currentTime = System.currentTimeMillis();
//        otpStorage.entrySet().removeIf(entry -> entry.getValue().getExpiryTime() < currentTime);
//    }

}
