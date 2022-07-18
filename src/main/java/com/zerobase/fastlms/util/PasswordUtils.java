package com.zerobase.fastlms.util;

import com.zerobase.fastlms.course.model.ServiceResult;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {
    
    public static boolean equals(String plaintext, String hashed) {
        if (plaintext == null || hashed == null ||
                plaintext.isEmpty() || hashed.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(plaintext, hashed);
    }
    
    public static String encPassword(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

}
