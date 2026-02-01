package navaneeth;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Custom Test Extension/Runner
 * 
 * This class uses @ExtendWith annotation to apply our custom extension
 * The CustomTestExtension will automatically log test execution details
 */
@ExtendWith(CustomTestExtension.class)
@DisplayName("Custom Test Extension Demonstration")
public class CustomExtensionTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Test: Addition with custom extension logging")
    void testAdditionWithCustomExtension() {
        // This test will be logged by CustomTestExtension
        int result = calculator.add(10, 20);
        assertEquals(30, result);
    }
    
    @Test
    @DisplayName("Test: Subtraction with custom extension logging")
    void testSubtractionWithCustomExtension() {
        // Custom extension will track execution time
        int result = calculator.subtract(50, 20);
        assertEquals(30, result);
    }
    
    @Test
    @DisplayName("Test: Multiplication with custom extension logging")
    void testMultiplicationWithCustomExtension() {
        int result = calculator.multiply(5, 6);
        assertEquals(30, result);
    }
    
    @Test
    @DisplayName("Test: Division with custom extension logging")
    void testDivisionWithCustomExtension() {
        double result = calculator.divide(100, 4);
        assertEquals(25.0, result, 0.01);
    }
    
    @Test
    @DisplayName("Test: Exception handling with custom extension")
    void testExceptionHandlingWithCustomExtension() {
        // Custom extension will log the exception details
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }
    
    @Nested
    @DisplayName("Nested Test Class with Custom Extension")
    class NestedTestClass {
        
        @Test
        @DisplayName("Nested Test: Custom extension works in nested classes too")
        void testNestedWithCustomExtension() {
            int result = calculator.add(1, 1);
            assertEquals(2, result);
        }
    }
}

