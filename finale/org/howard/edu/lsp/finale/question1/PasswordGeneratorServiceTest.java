package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 * Tests Singleton behavior, exception handling, and algorithm implementations.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // Verify that 'service' is not null
        assertNotNull(service, "PasswordGeneratorService instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Verify that both 'service' (created in @BeforeEach) 
        // and 'second' refer to the EXACT same object in memory. This 
        // test must confirm true singleton behavior — not just that the 
        // two objects are equal, but they are the *same* instance* returned by getInstance().
        assertSame(service, second, "getInstance() must return the exact same instance every time");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // Verify correct exception behavior: calling generatePassword without setting algorithm throws IllegalStateException
        assertThrows(IllegalStateException.class, () -> s.generatePassword(10),
                "generatePassword() should throw IllegalStateException when no algorithm is set");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        // Verify correct length
        assertEquals(10, p.length(), "Password length should be exactly 10");
        
        // Verify all characters are digits
        for (char c : p.toCharArray()) {
            assertTrue(Character.isDigit(c), "Basic algorithm should contain only digits (0-9), found: " + c);
        }
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        // Verify correct length
        assertEquals(12, p.length(), "Password length should be exactly 12");
        
        // Verify all characters are alphanumeric (A-Z, a-z, 0-9)
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (char c : p.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0, 
                    "Enhanced algorithm should contain only A-Z, a-z, 0-9, found: " + c);
        }
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        // Verify correct length
        assertEquals(8, p.length(), "Password length should be exactly 8");
        
        // Verify all characters are letters only (A-Z, a-z)
        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetter(c), "Letters algorithm should contain only letters (A-Z, a-z), found: " + c);
        }
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        // Generate with basic algorithm (digits only)
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        for (char c : p1.toCharArray()) {
            assertTrue(Character.isDigit(c), "Basic password should contain only digits");
        }

        // Switch to letters algorithm (letters only)
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        for (char c : p2.toCharArray()) {
            assertTrue(Character.isLetter(c), "Letters password should contain only letters");
        }

        // Switch to enhanced algorithm (letters and digits)
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (char c : p3.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0, "Enhanced password should contain A-Z, a-z, 0-9");
        }
        
        // Verify all passwords have correct length
        assertEquals(10, p1.length(), "p1 should have length 10");
        assertEquals(10, p2.length(), "p2 should have length 10");
        assertEquals(10, p3.length(), "p3 should have length 10");
    }
}
