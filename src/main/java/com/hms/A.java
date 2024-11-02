package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("testing", BCrypt.gensalt(4));
        System.out.println(hashpw);
    }
}
