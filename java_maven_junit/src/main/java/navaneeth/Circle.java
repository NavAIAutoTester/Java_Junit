package navaneeth;

/**
 * Circle class demonstrating ABSTRACTION implementation
 * - Extends abstract Shape class
 * - Must implement all abstract methods
 */
public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    // Implementation of abstract method
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    // Implementation of abstract method
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    public double getRadius() {
        return radius;
    }
}

