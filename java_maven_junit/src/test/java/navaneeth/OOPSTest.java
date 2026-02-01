package navaneeth;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive OOPS Concepts Test Suite
 * 
 * Demonstrates:
 * 1. ENCAPSULATION - Data hiding with private fields and public methods
 * 2. INHERITANCE - Classes extending other classes
 * 3. POLYMORPHISM - Method overriding and runtime polymorphism
 * 4. ABSTRACTION - Abstract classes and interfaces
 */
@ExtendWith(CustomTestExtension.class)
@DisplayName("OOPS Concepts Test Suite")
public class OOPSTest {
    
    // ========== ENCAPSULATION TESTS ==========
    
    @Test
    @DisplayName("Test: Encapsulation - Private fields with controlled access")
    void testEncapsulation() {
        BankAccount account = new BankAccount("ACC001", "John Doe", 1000.0);
        
        // Can access through public getters (encapsulation)
        assertEquals("ACC001", account.getAccountNumber());
        assertEquals("John Doe", account.getAccountHolderName());
        assertEquals(1000.0, account.getBalance(), 0.01);
        
        // Cannot directly access private fields - demonstrates data hiding
        // account.balance = 2000; // This would cause compilation error
    }
    
    @Test
    @DisplayName("Test: Encapsulation - Controlled modification through methods")
    void testEncapsulationWithMethods() {
        BankAccount account = new BankAccount("ACC002", "Jane Smith", 500.0);
        
        // Modify balance through controlled methods
        account.deposit(200.0);
        assertEquals(700.0, account.getBalance(), 0.01);
        
        account.withdraw(100.0);
        assertEquals(600.0, account.getBalance(), 0.01);
        
        // Invalid operations are prevented
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1000));
    }
    
    // ========== INHERITANCE TESTS ==========
    
    @Test
    @DisplayName("Test: Inheritance - SavingsAccount inherits from BankAccount")
    void testInheritance() {
        SavingsAccount savingsAccount = new SavingsAccount("SAV001", "Alice", 1000.0);
        
        // Inherited methods from BankAccount
        assertEquals("SAV001", savingsAccount.getAccountNumber());
        assertEquals("Alice", savingsAccount.getAccountHolderName());
        assertEquals(1000.0, savingsAccount.getBalance(), 0.01);
        
        // Can use inherited methods
        savingsAccount.deposit(500.0);
        assertEquals(1500.0, savingsAccount.getBalance(), 0.01);
        
        // Has its own specific methods
        assertNotNull(savingsAccount);
    }
    
    @Test
    @DisplayName("Test: Inheritance - CurrentAccount inherits from BankAccount")
    void testInheritanceCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("CUR001", "Bob", 2000.0, 500.0);
        
        // Inherited fields and methods
        assertEquals("CUR001", currentAccount.getAccountNumber());
        assertEquals(2000.0, currentAccount.getBalance(), 0.01);
        assertEquals(500.0, currentAccount.getOverdraftLimit(), 0.01);
    }
    
    @Test
    @DisplayName("Test: Inheritance - Employee and Manager hierarchy")
    void testEmployeeInheritance() {
        Employee employee = new Employee("John", 101, 50000.0);
        Manager manager = new Manager("Alice", 201, 80000.0, "IT");
        
        // Manager inherits from Employee
        assertEquals("Alice", manager.getName());
        assertEquals(201, manager.getEmployeeId());
        assertEquals(80000.0, manager.getSalary(), 0.01);

        assertEquals(50000.0, employee.getSalary(), 0.01);
        assertEquals("John", employee.getName());
        assertEquals(101, employee.getEmployeeId());
        
        // Manager has additional properties
        assertEquals("IT", manager.getDepartment());
    }
    
    // ========== POLYMORPHISM TESTS ==========
    
    @Test
    @DisplayName("Test: Polymorphism - Runtime method overriding")
    void testPolymorphismMethodOverriding() {
        BankAccount savingsAccount = new SavingsAccount("SAV002", "Charlie", 1000.0);
        BankAccount currentAccount = new CurrentAccount("CUR002", "Diana", 1000.0, 200.0);
        
        // Both are BankAccount references, but different implementations
        // Runtime polymorphism - JVM decides which method to call
        double savingsInterest = savingsAccount.calculateInterest();
        double currentInterest = currentAccount.calculateInterest();
        
        // SavingsAccount has 5% interest, CurrentAccount has 2%
        assertEquals(50.0, savingsInterest, 0.01); // 1000 * 0.05
        assertEquals(20.0, currentInterest, 0.01); // 1000 * 0.02
        
        // Different implementations for same method
        assertNotEquals(savingsInterest, currentInterest);
    }
    
    @Test
    @DisplayName("Test: Polymorphism - Using parent class reference")
    void testPolymorphismWithParentReference() {
        // Parent class reference pointing to child class objects
        BankAccount account1 = new SavingsAccount("SAV003", "Eve", 2000.0);
        BankAccount account2 = new CurrentAccount("CUR003", "Frank", 2000.0, 300.0);
        
        // Polymorphic behavior - same method call, different behavior
        double interest1 = account1.calculateInterest(); // Calls SavingsAccount implementation
        double interest2 = account2.calculateInterest(); // Calls CurrentAccount implementation
        
        assertTrue(interest1 > interest2); // Savings has higher interest
    }
    
    @Test
    @DisplayName("Test: Polymorphism - Employee bonus calculation")
    void testPolymorphismEmployeeBonus() {
        Employee employee = new Employee("John", 101, 50000.0);
        Employee manager = new Manager("Alice", 201, 80000.0, "HR");
        
        // Same method call, different implementations
        double empBonus = employee.calculateBonus(); // 10% of salary
        double mgrBonus = manager.calculateBonus();  // 20% of salary (overridden)
        
        assertEquals(5000.0, empBonus, 0.01); // 50000 * 0.1
        assertEquals(16000.0, mgrBonus, 0.01); // 80000 * 0.2
        
        // Manager gets higher bonus due to method overriding
        assertTrue(mgrBonus > empBonus);
    }
    
    @Test
    @DisplayName("Test: Polymorphism - Array of parent type with child objects")
    void testPolymorphismWithArray() {
        // Array of parent type containing child objects
        BankAccount[] accounts = {
            new SavingsAccount("SAV004", "George", 1000.0),
            new CurrentAccount("CUR004", "Helen", 1000.0, 200.0),
            new SavingsAccount("SAV005", "Ivan", 2000.0)
        };
        
        double totalInterest = 0;
        for (BankAccount account : accounts) {
            // Polymorphic method call - different implementation for each
            totalInterest += account.calculateInterest();
        }
        
        // Calculate expected: 1000*0.05 + 1000*0.02 + 2000*0.05 = 50 + 20 + 100 = 170
        assertEquals(170.0, totalInterest, 0.01);
    }
    
    // ========== ABSTRACTION TESTS ==========
    
    @Test
    @DisplayName("Test: Abstraction - Abstract class cannot be instantiated")
    void testAbstractionCannotInstantiate() {
        // Shape is abstract, cannot create instance directly
        // Shape shape = new Shape("red"); // This would cause compilation error
        
        // But can create instances of concrete subclasses
        Shape circle = new Circle("blue", 5.0);
        Shape rectangle = new Rectangle("green", 4.0, 6.0);
        
        assertNotNull(circle);
        assertNotNull(rectangle);
    }
    
    @Test
    @DisplayName("Test: Abstraction - Abstract methods must be implemented")
    void testAbstractionMethodImplementation() {
        Circle circle = new Circle("red", 3.0);
        Rectangle rectangle = new Rectangle("blue", 5.0, 4.0);
        
        // Abstract methods are implemented in subclasses
        double circleArea = circle.calculateArea();
        double circlePerimeter = circle.calculatePerimeter();
        
        double rectArea = rectangle.calculateArea();
        double rectPerimeter = rectangle.calculatePerimeter();
        
        // Verify calculations
        assertEquals(Math.PI * 3.0 * 3.0, circleArea, 0.01);
        assertEquals(2 * Math.PI * 3.0, circlePerimeter, 0.01);
        
        assertEquals(20.0, rectArea, 0.01); // 5 * 4
        assertEquals(18.0, rectPerimeter, 0.01); // 2 * (5 + 4)
    }
    
    @Test
    @DisplayName("Test: Abstraction - Polymorphism with abstract classes")
    void testAbstractionPolymorphism() {
        // Array of abstract type with concrete implementations
        Shape[] shapes = {
            new Circle("red", 2.0),
            new Rectangle("blue", 3.0, 4.0),
            new Circle("green", 1.5)
        };
        
        double totalArea = 0;
        for (Shape shape : shapes) {
            // Polymorphic call - each shape calculates area differently
            totalArea += shape.calculateArea();
        }
        
        // Expected: π*2² + 3*4 + π*1.5² = 4π + 12 + 2.25π = 6.25π + 12
        double expected = (Math.PI * 2.0 * 2.0) + (3.0 * 4.0) + (Math.PI * 1.5 * 1.5);
        assertEquals(expected, totalArea, 0.01);
    }
    
    @Test
    @DisplayName("Test: Abstraction - Concrete methods in abstract class")
    void testAbstractionConcreteMethods() {
        Circle circle = new Circle("yellow", 5.0);
        
        // Can use concrete methods from abstract class
        assertEquals("yellow", circle.getColor());
        circle.setColor("purple");
        assertEquals("purple", circle.getColor());
        
        // Concrete method that uses abstract methods
        String info = circle.getShapeInfo();
        assertTrue(info.contains("purple"));
        assertTrue(info.contains("Area"));
        assertTrue(info.contains("Perimeter"));
    }
    
    // ========== COMPREHENSIVE OOPS TEST ==========
    
    @Test
    @DisplayName("Test: All OOPS concepts working together")
    void testAllOOPConceptsTogether() {
        // ENCAPSULATION: Private fields with public accessors
        SavingsAccount savings = new SavingsAccount("SAV999", "Test User", 10000.0);
        
        // INHERITANCE: SavingsAccount extends BankAccount
        assertTrue(savings instanceof BankAccount);
        
        // POLYMORPHISM: Method overriding
        BankAccount accountRef = savings;
        double interest = accountRef.calculateInterest(); // Calls SavingsAccount implementation
        
        // ABSTRACTION: Using abstract Shape class
        Shape shape = new Circle("red", 10.0);
        double area = shape.calculateArea(); // Calls Circle implementation
        
        // Verify all concepts work together
        assertEquals(500.0, interest, 0.01); // 10000 * 0.05
        assertEquals(Math.PI * 100.0, area, 0.01); // π * 10²
        
        // Encapsulation: Controlled access
        savings.deposit(1000.0);
        assertEquals(11000.0, savings.getBalance(), 0.01);
    }
}

