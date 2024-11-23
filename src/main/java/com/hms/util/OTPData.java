package com.hms.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPData {
    private final String otp;
    private final long expiryTime;

    public OTPData(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

}
