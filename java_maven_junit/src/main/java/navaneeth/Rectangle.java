package navaneeth;

/**
 * Rectangle class demonstrating ABSTRACTION implementation
 * - Extends abstract Shape class
 * - Implements abstract methods with different logic than Circle
 */
public class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }
    
    // Implementation of abstract method
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    // Implementation of abstract method
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
}

