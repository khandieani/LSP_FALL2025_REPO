package org.howard.edu.lsp.midterm.question2;

/*
 * AreaCalculator provides four overloaded area() methods:
 * - area(double side) for square
 * - area(double length, double width) for rectangle
 * - area(double base, double height, boolean isTriangle) for triangle
 * - area(double majorAxis, double minorAxis, int oval) for ellipse
 *
 * Overloading is preferable to separate names here because the concept (computing
 * an area) is the same for all shapes; overloading keeps a single logical method
 * name and lets the parameter list disambiguate behavior, improving readability
 * and discoverability.
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
