package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Correct calculations
            System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
            System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
            System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
            System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Exception demonstration
        try {
            System.out.println("Invalid circle test 10 area = " + AreaCalculator.area(-3.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        /*
         * Explanation:
         * Method overloading is preferred here because it allows multiple versions
         * of the same logical operation ('area') to exist for different shapes.
         * This improves readability and organization, since the method name reflects
         * the shared concept of calculating area instead of creating separate,
         * repetitive names like circleArea() or rectangleArea().
         */
    }
}
