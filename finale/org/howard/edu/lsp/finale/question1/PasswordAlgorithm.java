package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 * Defines the contract that all password generation strategies must implement.
 */
public interface PasswordAlgorithm {
    /**
     * Generates a password of the specified length using this algorithm's strategy.
     *
     * @param length the desired length of the generated password
     * @return a password string of the specified length
     */
    String generatePassword(int length);
}
