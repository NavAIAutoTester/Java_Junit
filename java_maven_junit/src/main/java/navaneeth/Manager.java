package navaneeth;

/**
 * Manager class demonstrating INHERITANCE and POLYMORPHISM
 * - Extends Employee
 * - Overrides calculateBonus with different logic
 */
public class Manager extends Employee {
    private String department;
    
    public Manager(String name, int employeeId, double salary, String department) {
        super(name, employeeId, salary);
        this.department = department;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    // Method Overriding - POLYMORPHISM
    @Override
    public double calculateBonus() {
        return getSalary() * 0.2; // Managers get 20% bonus
    }
    
    @Override
    public String toString() {
        return "Manager{id=" + getEmployeeId() + ", name='" + getName() + 
               "', salary=" + getSalary() + ", department='" + department + "'}";
    }
}

