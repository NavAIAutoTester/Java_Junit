package navaneeth;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive JUnit 5 Test Example demonstrating all hook methods
 * 
 * This test class demonstrates:
 * - @BeforeAll: Runs once before all tests
 * - @AfterAll: Runs once after all tests
 * - @BeforeEach: Runs before each test method
 * - @AfterEach: Runs after each test method
 * - @Test: Marks a method as a test case
 * - @DisplayName: Provides a custom display name for the test
 * - @Disabled: Skips a test
 */
@DisplayName("Calculator Test Suite")
public class CalculatorTest {
    
    private Calculator calculator;
    private static int testCounter = 0;
    
    /**
     * @BeforeAll - This method runs ONCE before ALL test methods in the class
     * - Must be static
     * - Used for expensive setup operations (database connections, file I/O, etc.)
     * - Executed before any test method
     */
    @BeforeAll
    @DisplayName("Setup: Initialize test environment")
    static void setUpBeforeAll() {
        System.out.println("=========================================");
        System.out.println("@BeforeAll: Setting up test environment");
        System.out.println("This runs ONCE before all tests");
        System.out.println("=========================================");
        testCounter = 0;
    }
    
    /**
     * @AfterAll - This method runs ONCE after ALL test methods in the class
     * - Must be static
     * - Used for cleanup operations (closing connections, deleting temp files, etc.)
     * - Executed after all test methods complete
     */
    @AfterAll
    @DisplayName("Teardown: Cleanup test environment")
    static void tearDownAfterAll() {
        System.out.println("=========================================");
        System.out.println("@AfterAll: Cleaning up test environment");
        System.out.println("Total tests executed: " + testCounter);
        System.out.println("This runs ONCE after all tests");
        System.out.println("=========================================");
    }
    
    /**
     * @BeforeEach - This method runs BEFORE EACH test method
     * - Not static (instance method)
     * - Used to set up test data or initialize objects before each test
     * - Ensures each test starts with a clean state
     */
    @BeforeEach
    @DisplayName("Setup: Initialize Calculator instance")
    void setUp() {
        System.out.println("\n--- @BeforeEach: Creating new Calculator instance ---");
        calculator = new Calculator();
        testCounter++;
        System.out.println("Test #" + testCounter + " is about to run");
    }
    
    /**
     * @AfterEach - This method runs AFTER EACH test method
     * - Not static (instance method)
     * - Used to clean up after each test (reset state, close resources, etc.)
     * - Ensures no test affects another test
     */
    @AfterEach
    @DisplayName("Teardown: Cleanup after test")
    void tearDown() {
        System.out.println("--- @AfterEach: Cleaning up after test ---");
        calculator = null;
        System.out.println("Calculator instance set to null");
    }
    
    /**
     * @Test - Marks a method as a test case
     * - Each @Test method is executed independently
     * - Order of execution is not guaranteed (unless using @TestMethodOrder)
     */
    @Test
    @DisplayName("Test: Addition of two positive numbers")
    void testAddition() {
        System.out.println("Executing testAddition()");
        int result = calculator.add(5, 3);
        assertEquals(8, result, "5 + 3 should equal 8");
        System.out.println("Addition test passed: 5 + 3 = " + result);
    }
    
    @Test
    @DisplayName("Test: Subtraction of two numbers")
    void testSubtraction() {
        System.out.println("Executing testSubtraction()");
        int result = calculator.subtract(10, 4);
        assertEquals(6, result, "10 - 4 should equal 6");
        System.out.println("Subtraction test passed: 10 - 4 = " + result);
    }
    
    @Test
    @DisplayName("Test: Multiplication of two numbers")
    void testMultiplication() {
        System.out.println("Executing testMultiplication()");
        int result = calculator.multiply(6, 7);
        assertEquals(42, result, "6 * 7 should equal 42");
        System.out.println("Multiplication test passed: 6 * 7 = " + result);
    }
    
    @Test
    @DisplayName("Test: Division of two numbers")
    void testDivision() {
        System.out.println("Executing testDivision()");
        double result = calculator.divide(15, 3);
        assertEquals(5.0, result, 0.001, "15 / 3 should equal 5.0");
        System.out.println("Division test passed: 15 / 3 = " + result);
    }
    
    @Test
    @DisplayName("Test: Division by zero should throw exception")
    void testDivisionByZero() {
        System.out.println("Executing testDivisionByZero()");
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(10, 0),
            "Dividing by zero should throw IllegalArgumentException"
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
        System.out.println("Division by zero test passed: Exception thrown correctly");
    }
    
    @Test
    @DisplayName("Test: Check if number is even")
    void testIsEven() {
        System.out.println("Executing testIsEven()");
        assertTrue(calculator.isEven(4), "4 should be even");
        assertTrue(calculator.isEven(0), "0 should be even");
        assertFalse(calculator.isEven(5), "5 should not be even");
        System.out.println("IsEven test passed");
    }
    
    @Test
    @DisplayName("Test: Multiple assertions in one test")
    void testMultipleOperations() {
        System.out.println("Executing testMultipleOperations()");
        assertAll("Multiple calculator operations",
            () -> assertEquals(10, calculator.add(4, 6)),
            () -> assertEquals(2, calculator.subtract(8, 6)),
            () -> assertEquals(24, calculator.multiply(4, 6)),
            () -> assertEquals(2.0, calculator.divide(12, 6), 0.001)
        );
        System.out.println("Multiple operations test passed");
    }
    
    /**
     * @Disabled - This annotation skips the test
     * - Useful for temporarily disabling tests that are under development
     * - The test will not be executed
     */
    @Test
    @Disabled("This test is disabled for demonstration purposes")
    @DisplayName("Test: This test is disabled")
    void testDisabled() {
        System.out.println("This test should not run");
        fail("This test should be disabled");
    }
    
    /**
     * Example of nested test class
     * Demonstrates @Nested annotation for organizing related tests
     */
    @Nested
    @DisplayName("Nested Test Class: Advanced Operations")
    class AdvancedOperationsTest {
        
        @BeforeEach
        void nestedSetUp() {
            System.out.println("  [Nested] @BeforeEach in nested class");
        }
        
        @AfterEach
        void nestedTearDown() {
            System.out.println("  [Nested] @AfterEach in nested class");
        }
        
        @Test
        @DisplayName("Nested Test: Complex calculation")
        void testComplexCalculation() {
            System.out.println("  [Nested] Executing complex calculation test");
            int result = calculator.add(
                calculator.multiply(2, 3),
                calculator.subtract(10, 4)
            );
            assertEquals(12, result);
            System.out.println("  [Nested] Complex calculation test passed");
        }
    }
}

