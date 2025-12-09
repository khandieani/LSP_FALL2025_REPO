package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generation algorithm using java.util.Random.
 * Generates passwords containing digits only (0-9).
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {

    private static final String DIGITS = "0123456789";
    private final Random random;

    /**
     * Initializes the BasicPasswordAlgorithm with a new Random instance.
     */
    public BasicPasswordAlgorithm() {
        this.random = new Random();
    }

    /**
     * Generates a password containing only digits (0-9).
     *
     * @param length the desired length of the password
     * @return a password string of the specified length containing only digits
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}
