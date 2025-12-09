package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generation algorithm using java.security.SecureRandom.
 * Generates passwords containing uppercase letters (A-Z), lowercase letters (a-z), and digits (0-9).
 */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {

    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom secureRandom;

    /**
     * Initializes the EnhancedPasswordAlgorithm with a new SecureRandom instance.
     */
    public EnhancedPasswordAlgorithm() {
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generates a password containing uppercase letters, lowercase letters, and digits.
     *
     * @param length the desired length of the password
     * @return a password string of the specified length containing mixed characters
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED_CHARS.length());
            password.append(ALLOWED_CHARS.charAt(index));
        }
        return password.toString();
    }
}
