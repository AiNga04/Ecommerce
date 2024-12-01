package org.example.ecommerce.domain.common.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}