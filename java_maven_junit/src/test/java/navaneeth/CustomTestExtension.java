package navaneeth;

import org.junit.jupiter.api.extension.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom JUnit 5 Extension demonstrating how to create a custom test runner/extension
 * 
 * This extension:
 * - Logs test execution start and end times
 * - Tracks test execution duration
 * - Provides custom behavior before and after tests
 * - Can be reused across multiple test classes
 */
public class CustomTestExtension implements BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback, TestExecutionExceptionHandler {
    
    private static final Map<String, Long> testStartTimes = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Called before all tests in a test class
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String className = context.getRequiredTestClass().getSimpleName();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CUSTOM EXTENSION: Starting test class: " + className);
        System.out.println("Time: " + LocalDateTime.now().format(formatter));
        System.out.println("=".repeat(60));
    }
    
    /**
     * Called after all tests in a test class
     */
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        String className = context.getRequiredTestClass().getSimpleName();
        System.out.println("=".repeat(60));
        System.out.println("CUSTOM EXTENSION: Completed test class: " + className);
        System.out.println("Time: " + LocalDateTime.now().format(formatter));
        System.out.println("=".repeat(60) + "\n");
    }
    
    /**
     * Called before each test method
     */
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        String className = context.getRequiredTestClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        testStartTimes.put(testName, startTime);
        
        System.out.println("\n[Custom Extension] Starting test: " + testName + " in " + className);
        System.out.println("[Custom Extension] Start time: " + LocalDateTime.now().format(formatter));
    }
    
    /**
     * Called after each test method
     */
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        long startTime = testStartTimes.getOrDefault(testName, System.currentTimeMillis());
        long duration = System.currentTimeMillis() - startTime;
        
        System.out.println("[Custom Extension] Completed test: " + testName);
        System.out.println("[Custom Extension] Duration: " + duration + " ms");
        System.out.println("[Custom Extension] End time: " + LocalDateTime.now().format(formatter));
        
        testStartTimes.remove(testName);
    }
    
    /**
     * Called when a test throws an exception
     * This allows custom handling of test failures
     */
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String testName = context.getDisplayName();
        System.out.println("[Custom Extension] Test failed: " + testName);
        System.out.println("[Custom Extension] Exception: " + throwable.getClass().getSimpleName());
        System.out.println("[Custom Extension] Message: " + throwable.getMessage());
        
        // Re-throw to let JUnit handle it normally
        throw throwable;
    }
}

