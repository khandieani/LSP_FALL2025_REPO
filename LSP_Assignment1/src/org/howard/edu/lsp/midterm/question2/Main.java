package org.howard.edu.lsp.midterm.question2;

public class Main {
    /*
     * Method overloading is preferable to separate method names (like circleArea, rectangleArea)
     * because it provides a unified interface for the same conceptual operation (computing area)
     * while letting parameter types naturally distinguish the variants. This makes the API more
     * discoverable and maintainable than having separate names for each shape.
     */
    public static void main(String[] args) {
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10.0, 6.0, true));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        try {
            AreaCalculator.area(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
