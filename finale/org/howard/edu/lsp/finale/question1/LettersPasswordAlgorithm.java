package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters-only password generation algorithm using java.util.Random.
 * Generates passwords containing letters only (A-Z, a-z).
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final Random random;

    /**
     * Initializes the LettersPasswordAlgorithm with a new Random instance.
     */
    public LettersPasswordAlgorithm() {
        this.random = new Random();
    }

    /**
     * Generates a password containing only letters (A-Z, a-z).
     *
     * @param length the desired length of the password
     * @return a password string of the specified length containing only letters
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}
