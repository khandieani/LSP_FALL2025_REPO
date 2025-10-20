package org.howard.edu.lsp.midterm.question2;

/**
 * AreaCalculator provides methods for computing areas of different shapes.
 */
public class AreaCalculator {
    // Circle area = π × r²
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be > 0");
        }
        return Math.PI * radius * radius;
    }

    // Rectangle area = width × height
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be > 0");
        }
        return width * height;
    }

    // Triangle area = ½ × base × height
    public static double area(double base, double height, boolean isTriangle) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be > 0");
        }
        return 0.5 * base * height;
    }

    // Square area = side²
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be > 0");
        }
        return (double) side * side;
    }
}