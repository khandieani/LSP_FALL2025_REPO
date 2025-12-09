package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
  Service responsible for generating passwords using different selectable algorithms.

  DESIGN PATTERN NOTES

  Patterns Used:

  1. Singleton
     The application only needs one shared password generator.
     getInstance provides the single access point.
     The constructor is private so no other instances can be created.

  2. Strategy
     Each password-generation approach is its own class.
     setAlgorithm allows switching between algorithms at runtime.
     All algorithms follow the same PasswordAlgorithm interface, making them easy to swap or extend.

  Why These Patterns Work:

  Singleton fits because the requirements call for one shared access point, and keeping a single instance avoids inconsistent state.

  Strategy fits because multiple password-generation approaches are required, the caller must be able to change algorithms while the program is running, and new algorithms can be added later without changing client code. It also keeps each algorithm separate and easy to maintain.

  Together, these patterns meet all system expectations:
  They support multiple approaches, allow runtime selection, make future extensions simple, allow password behavior to be swapped, and provide a single shared access point.
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
