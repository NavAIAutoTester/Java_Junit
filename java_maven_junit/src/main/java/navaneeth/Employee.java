package navaneeth;

/**
 * Employee class demonstrating OOPS concepts
 * - Encapsulation: Private fields with getters/setters
 * - Used in polymorphism examples
 */
public class Employee {
    private String name;
    private int employeeId;
    private double salary;
    
    public Employee(String name, int employeeId, double salary) {
        this.name = name;
        this.employeeId = employeeId;
        this.salary = salary;
    }
    
    // Encapsulation - getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    // Method that can be overridden
    public double calculateBonus() {
        return salary * 0.1; // Default 10% bonus
    }
    
    @Override
    public String toString() {
        return "Employee{id=" + employeeId + ", name='" + name + "', salary=" + salary + "}";
    }
}

