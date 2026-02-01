package navaneeth;

/**
 * Abstract Shape class demonstrating ABSTRACTION
 * - Cannot be instantiated directly
 * - Defines contract that subclasses must implement
 */
public abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract methods - must be implemented by subclasses
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // Concrete method - shared by all subclasses
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    // Concrete method
    public String getShapeInfo() {
        return "Shape color: " + color + ", Area: " + calculateArea() + ", Perimeter: " + calculatePerimeter();
    }
}

