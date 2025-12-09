package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for generating passwords using selectable algorithms.
 * 
 * DESIGN PATTERN DOCUMENTATION:
 * =============================
 * 
 * PATTERNS USED:
 * 1. SINGLETON PATTERN
 *    - Ensures only one instance of PasswordGeneratorService exists
 *    - Provides global access point via getInstance()
 *    - Uses private constructor and static instance
 * 
 * 2. STRATEGY PATTERN
 *    - Encapsulates password generation algorithms in separate classes
 *    - Allows algorithm selection at runtime via setAlgorithm()
 *    - Enables swapping algorithms without changing client code
 *    - Each algorithm implements the PasswordAlgorithm interface
 * 
 * WHY THESE PATTERNS:
 * 
 * The Singleton pattern is appropriate because:
 * - System architects require "a single shared access point"
 * - Only one instance of the service should exist across the application
 * - Prevents multiple instances with inconsistent state
 * - Ensures resource efficiency (single Random/SecureRandom instance per algorithm)
 * 
 * The Strategy pattern is appropriate because:
 * - Multiple password generation approaches must be supported
 * - Algorithm selection must be changeable at runtime
 * - Future expansion of algorithms must not require modification to client code
 * - Password generation behavior must be swappable
 * - Clean separation of concerns: each algorithm is independent
 * 
 * Together, these patterns satisfy all architect requirements:
 * 1. ✓ Support multiple approaches: Strategy pattern with three implementations
 * 2. ✓ Runtime selection: setAlgorithm() allows dynamic selection
 * 3. ✓ Future expansion: Add new PasswordAlgorithm implementations without client changes
 * 4. ✓ Swappable behavior: Strategy pattern enables behavior substitution
 * 5. ✓ Single access point: Singleton provides getInstance()
 */
public class PasswordGeneratorService {

    private static PasswordGeneratorService instance;
    private final Map<String, PasswordAlgorithm> algorithms;
    private PasswordAlgorithm currentAlgorithm;

    /**
     * Private constructor to prevent external instantiation (Singleton pattern).
     * Initializes the algorithm registry with available password generation strategies.
     */
    private PasswordGeneratorService() {
        this.algorithms = new HashMap<>();
        this.algorithms.put("basic", new BasicPasswordAlgorithm());
        this.algorithms.put("enhanced", new EnhancedPasswordAlgorithm());
        this.algorithms.put("letters", new LettersPasswordAlgorithm());
        this.currentAlgorithm = null;
    }

    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * Creates the instance on first call, subsequent calls return the same instance.
     *
     * @return the single instance of PasswordGeneratorService
     */
    public static synchronized PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * Sets the password generation algorithm by name.
     * The algorithm must be registered in the algorithms map.
     *
     * @param name the name of the algorithm to use ("basic", "enhanced", or "letters")
     * @throws IllegalArgumentException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        if (!algorithms.containsKey(name)) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        this.currentAlgorithm = algorithms.get(name);
    }

    /**
     * Generates a password of the specified length using the currently selected algorithm.
     *
     * @param length the desired length of the generated password
     * @return a password string of the specified length
     * @throws IllegalStateException if no algorithm has been set via setAlgorithm()
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        return currentAlgorithm.generatePassword(length);
    }
}
