# JUnit Testing Framework - Comprehensive Guide

## Table of Contents
1. [What is JUnit?](#what-is-junit)
2. [Uses of JUnit](#uses-of-junit)
3. [JUnit Hooks (Lifecycle Methods)](#junit-hooks-lifecycle-methods)
4. [How to Execute Tests](#how-to-execute-tests)
5. [JUnit Reports](#junit-reports)
6. [Common Maven Commands for JUnit](#common-maven-commands-for-junit)

---

## What is JUnit?

**JUnit** is a popular open-source testing framework for Java programming language. It is used to write and run repeatable automated tests to ensure that code works as expected.

### Key Features:
- **Unit Testing Framework**: Primarily designed for unit testing individual components of code
- **Annotations-based**: Uses Java annotations to define test methods and lifecycle hooks
- **Assertions**: Provides a rich set of assertion methods to verify expected behavior
- **Test Runners**: Executes tests and reports results
- **Integration**: Works seamlessly with build tools like Maven, Gradle, and IDEs like IntelliJ IDEA, Eclipse

### JUnit Versions:
- **JUnit 3.x**: Old version, uses inheritance-based approach
- **JUnit 4.x**: Introduced annotations, widely used
- **JUnit 5.x (Jupiter)**: Latest version, modular architecture, improved annotations

This project uses **JUnit 5 (Jupiter)**, which is the current standard.

---

## Uses of JUnit

JUnit is used for various testing purposes:

### 1. **Unit Testing**
   - Test individual methods and classes in isolation
   - Verify that each unit of code performs correctly
   - Example: Testing a Calculator's add() method

### 2. **Regression Testing**
   - Ensure that new changes don't break existing functionality
   - Run test suite after code modifications

### 3. **Test-Driven Development (TDD)**
   - Write tests before implementing functionality
   - Helps design better code structure

### 4. **Integration Testing**
   - Test interactions between multiple components
   - Verify that integrated parts work together

### 5. **Automated Testing**
   - Run tests automatically in CI/CD pipelines
   - Catch bugs early in the development cycle

### 6. **Documentation**
   - Tests serve as executable documentation
   - Show how code is expected to be used

### 7. **Code Quality**
   - Encourages writing testable code
   - Improves code design and maintainability

---

## JUnit Hooks (Lifecycle Methods)

JUnit provides several **hook methods** (also called lifecycle methods) that allow you to execute code at specific points during the test execution lifecycle. These hooks help in setting up and tearing down test environments.

### Hook Methods in JUnit 5:

#### 1. **@BeforeAll**
- **Purpose**: Executes once before all test methods in the test class
- **When**: Before any test method runs
- **Must be**: `static` method
- **Use Cases**:
  - Setting up database connections
  - Initializing expensive resources
  - Loading configuration files
  - Creating shared test data

**Example:**
```java
@BeforeAll
static void setUpBeforeAll() {
    System.out.println("This runs ONCE before all tests");
    // Initialize database connection
    // Load test configuration
}
```

#### 2. **@AfterAll**
- **Purpose**: Executes once after all test methods in the test class
- **When**: After all test methods complete
- **Must be**: `static` method
- **Use Cases**:
  - Closing database connections
  - Cleaning up shared resources
  - Generating final reports
  - Deleting temporary files

**Example:**
```java
@AfterAll
static void tearDownAfterAll() {
    System.out.println("This runs ONCE after all tests");
    // Close database connection
    // Clean up resources
}
```

#### 3. **@BeforeEach**
- **Purpose**: Executes before each test method
- **When**: Before every `@Test` method
- **Must be**: Instance method (not static)
- **Use Cases**:
  - Creating fresh test objects
  - Resetting test data
  - Initializing instance variables
  - Setting up test fixtures

**Example:**
```java
@BeforeEach
void setUp() {
    System.out.println("This runs BEFORE each test");
    calculator = new Calculator(); // Fresh instance for each test
}
```

#### 4. **@AfterEach**
- **Purpose**: Executes after each test method
- **When**: After every `@Test` method completes
- **Must be**: Instance method (not static)
- **Use Cases**:
  - Cleaning up after each test
  - Resetting state
  - Closing file handles
  - Clearing test data

**Example:**
```java
@AfterEach
void tearDown() {
    System.out.println("This runs AFTER each test");
    calculator = null; // Clean up
}
```

### Execution Order:

```
@BeforeAll (once)
    ↓
@BeforeEach → @Test → @AfterEach
    ↓
@BeforeEach → @Test → @AfterEach
    ↓
@BeforeEach → @Test → @AfterEach
    ↓
@AfterAll (once)
```

### Other Important Annotations:

- **@Test**: Marks a method as a test case
- **@DisplayName**: Provides a custom name for the test (shown in reports)
- **@Disabled**: Skips a test (useful for tests under development)
- **@Nested**: Organizes related tests in nested classes
- **@Tag**: Categorizes tests for filtering

---

## How to Execute Tests

### Method 1: Using Maven Command Line

```bash
# Run all tests
mvn test

# Run tests for a specific class
mvn test -Dtest=CalculatorTest

# Run a specific test method
mvn test -Dtest=CalculatorTest#testAddition

# Run tests matching a pattern
mvn test -Dtest=*Test

# Run tests with specific tags
mvn test -Dgroups=smoke

# Combine test execution with report generation (single command)
mvn test -Dtest=OOPSTest surefire-report:report
# This runs the specified test class and then generates HTML report
```

### Method 2: Using IDE

#### IntelliJ IDEA:
1. Right-click on test class → "Run 'CalculatorTest'"
2. Click green arrow next to test method
3. Use keyboard shortcut: `Ctrl+Shift+F10` (Windows/Linux) or `Cmd+Shift+R` (Mac)

#### Eclipse:
1. Right-click on test class → "Run As" → "JUnit Test"
2. Use keyboard shortcut: `Alt+Shift+X, T`

### Method 3: Using Java Command Line

```bash
# Compile the project
mvn compile test-compile

# Run using JUnit Console Launcher (JUnit 5)
java -jar junit-platform-console-standalone.jar --class-path target/test-classes --scan-class-path
```

### Method 4: Using Maven Surefire Plugin

The Maven Surefire Plugin automatically runs tests during the `test` phase of the Maven lifecycle.

---

## JUnit Reports

JUnit generates test reports that provide detailed information about test execution results.

### Report Locations:

#### 1. **Console Output**
- Real-time output during test execution
- Shows test progress, failures, and summary

#### 2. **Maven Surefire Reports**
- **Location**: `target/surefire-reports/`
- **Files**:
  - `TEST-*.xml`: XML format test results
  - `*.txt`: Plain text test results

**Example Output:**
```
Tests run: 8, Failures: 0, Errors: 0, Skipped: 1
```

#### 3. **HTML Reports (with Maven Surefire Report Plugin)**
- **Location**: `target/site/surefire-report.html`
- **Generated by**: `mvn surefire-report:report`
- **Contains**:
  - Test summary
  - Passed/Failed/Skipped tests
  - Execution time
  - Error messages and stack traces

#### 4. **IDE Test Results**
- IntelliJ IDEA: Test Results panel
- Eclipse: JUnit view

### Report Information Includes:
- **Test Count**: Total number of tests
- **Passed Tests**: Successfully executed tests
- **Failed Tests**: Tests that did not meet assertions
- **Skipped Tests**: Tests marked with @Disabled
- **Execution Time**: Time taken for each test and total time
- **Error Details**: Stack traces for failures
- **Test Names**: Display names of tests

### Generating HTML Reports:

```bash
# Generate HTML surefire report (after tests have run)
mvn surefire-report:report

# Combine test execution and report generation in one command
mvn test -Dtest=OOPSTest surefire-report:report
# This runs OOPSTest and immediately generates the HTML report

# For all tests with report generation
mvn test surefire-report:report

# View the report
# Open: target/site/surefire-report.html in a browser
```

**Note**: When combining commands, Maven executes them sequentially. The test phase must complete successfully before the report generation begins.

---

## Common Maven Commands for JUnit

Maven provides several commands to compile, test, and manage your Java project with JUnit tests.

### 1. **mvn clean**
- **Purpose**: Removes the `target` directory
- **Use**: Clean previous build artifacts
- **Example**: `mvn clean`

### 2. **mvn compile**
- **Purpose**: Compiles main source code
- **Use**: Compile Java files in `src/main/java`
- **Example**: `mvn compile`

### 3. **mvn test-compile**
- **Purpose**: Compiles test source code
- **Use**: Compile Java files in `src/test/java`
- **Example**: `mvn test-compile`

### 4. **mvn test**
- **Purpose**: Compiles and runs all tests
- **What it does**:
  1. Compiles main source code
  2. Compiles test source code
  3. Executes all tests using Surefire plugin
  4. Generates test reports in `target/surefire-reports/`
- **Example**: `mvn test`
- **Output**: Console summary and XML/text reports

### 5. **mvn test -Dtest=ClassName**
- **Purpose**: Run tests for a specific class
- **Example**: `mvn test -Dtest=CalculatorTest`
- **Note**: Wildcards supported: `mvn test -Dtest=*Test`

### 6. **mvn test -Dtest=ClassName#methodName**
- **Purpose**: Run a specific test method
- **Example**: `mvn test -Dtest=CalculatorTest#testAddition`

### 7. **mvn test -Dtest="ClassName1,ClassName2"**
- **Purpose**: Run tests from multiple classes
- **Example**: `mvn test -Dtest="CalculatorTest,AppTest"`

### 8. **mvn clean test**
- **Purpose**: Clean and run tests
- **Use**: Ensures fresh build before testing
- **Example**: `mvn clean test`

### 9. **mvn package**
- **Purpose**: Compiles, tests, and packages the project
- **What it does**:
  1. Runs `compile`, `test`, and `package` phases
  2. Creates JAR/WAR file in `target/`
- **Note**: Tests must pass for packaging to succeed
- **Example**: `mvn package`

### 10. **mvn install**
- **Purpose**: Compiles, tests, packages, and installs to local repository
- **What it does**:
  1. Runs all previous phases
  2. Installs artifact to `~/.m2/repository/`
- **Example**: `mvn install`

### 11. **mvn surefire-report:report**
- **Purpose**: Generates HTML test report
- **Output**: `target/site/surefire-report.html`
- **Example**: `mvn surefire-report:report`
- **Note**: Requires `maven-surefire-report-plugin` in pom.xml

### 11a. **mvn test -Dtest=ClassName surefire-report:report**
- **Purpose**: Run specific test class and generate HTML report in one command
- **What it does**:
  1. Runs the `test` phase with the specified test class filter
  2. Executes the `surefire-report:report` goal to generate HTML report
- **Example**: `mvn test -Dtest=OOPSTest surefire-report:report`
- **Benefits**: 
  - Single command execution
  - Automatically generates report after test execution
  - No need to run two separate commands
- **Output**: 
  - Test results in console
  - HTML report at `target/site/surefire-report.html`
- **Note**: In Maven, you can chain multiple goals/phases in a single command. They execute sequentially.

### 12. **mvn test -DskipTests**
- **Purpose**: Skip test execution (but still compile tests)
- **Use**: When you want to build without running tests
- **Example**: `mvn package -DskipTests`

### 13. **mvn test -Dmaven.test.skip=true**
- **Purpose**: Skip test compilation and execution
- **Use**: Fastest way to skip all test-related activities
- **Example**: `mvn package -Dmaven.test.skip=true`

### 14. **mvn verify**
- **Purpose**: Runs integration tests and verifies package
- **What it does**: Runs all phases up to `verify`
- **Example**: `mvn verify`

### Command Execution Flow:

```
mvn test
  ↓
[validate] → [compile] → [test-compile] → [test] → [package]
                                    ↓
                            Run JUnit Tests
                                    ↓
                            Generate Reports
```

### Maven Lifecycle Phases (Relevant to Testing):

1. **validate**: Validate project structure
2. **compile**: Compile main source code
3. **test-compile**: Compile test source code
4. **test**: Run unit tests
5. **package**: Package compiled code
6. **verify**: Run integration tests
7. **install**: Install to local repository
8. **deploy**: Deploy to remote repository

### Tips for Maven Commands:

1. **Verbose Output**: Add `-X` for debug output
   ```bash
   mvn test -X
   ```

2. **Skip Tests**: Use `-DskipTests` to build without running tests
   ```bash
   mvn package -DskipTests
   ```

3. **Run Specific Tests**: Use `-Dtest` parameter
   ```bash
   mvn test -Dtest=CalculatorTest
   ```

4. **Parallel Execution**: Configure Surefire plugin for parallel test execution
   ```xml
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <configuration>
           <parallel>methods</parallel>
           <threadCount>4</threadCount>
       </configuration>
   </plugin>
   ```

5. **View Test Output**: Check `target/surefire-reports/` directory after test execution

---

## Project Structure

```
java_maven_junit/
├── pom.xml                          # Maven configuration with JUnit dependency
├── src/
│   ├── main/
│   │   └── java/
│   │       └── navaneeth/
│   │           ├── App.java        # Main application class
│   │           └── Calculator.java # Calculator class for testing
│   └── test/
│       └── java/
│           └── navaneeth/
│               ├── AppTest.java           # Simple test example
│               └── CalculatorTest.java    # Comprehensive test with all hooks
└── JUNIT_GUIDE.md                  # This documentation file
```

---

## Running the Example Tests

To run the example tests in this project:

```bash
# Navigate to project directory
cd java_maven_junit

# Run all tests
mvn test

# Run only CalculatorTest
mvn test -Dtest=CalculatorTest

# Run specific test method
mvn test -Dtest=CalculatorTest#testAddition

# Generate HTML report
mvn surefire-report:report

# Combine test execution and report generation (single command)
mvn test -Dtest=OOPSTest surefire-report:report
# This runs OOPSTest and immediately generates the HTML report
```

---

## Summary

- **JUnit** is a powerful testing framework for Java
- **Hooks** (@BeforeAll, @AfterAll, @BeforeEach, @AfterEach) help manage test lifecycle
- **Maven** provides commands to compile, test, and generate reports
- **Reports** provide detailed information about test execution
- **Best Practice**: Write tests for all critical functionality and run them regularly

---

## Additional Resources

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [JUnit 5 API Documentation](https://junit.org/junit5/docs/current/api/)

---

**Happy Testing! 🧪**

