# Java Maven JUnit Project

A comprehensive Java Maven project demonstrating JUnit 5 testing framework with all lifecycle hooks and best practices.

## Project Information

- **Group ID**: navaneeth
- **Artifact ID**: java_maven_junit
- **Version**: 1.0-SNAPSHOT
- **Java Version**: 21
- **JUnit Version**: 5.10.0

## Project Structure

```
java_maven_junit/
├── pom.xml                          # Maven configuration
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
├── JUNIT_GUIDE.md                  # Comprehensive JUnit documentation
└── README.md                       # This file
```

## Quick Start

### Prerequisites
- Java JDK 21 or higher
- Maven 3.6 or higher

### Running Tests

```bash
# Navigate to project directory
cd java_maven_junit

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Run specific test method
mvn test -Dtest=CalculatorTest#testAddition

# Clean and run tests
mvn clean test
```

## Features Demonstrated

### JUnit 5 Hook Methods
- ✅ `@BeforeAll` - Runs once before all tests
- ✅ `@AfterAll` - Runs once after all tests
- ✅ `@BeforeEach` - Runs before each test method
- ✅ `@AfterEach` - Runs after each test method

### Test Examples
- Basic arithmetic operations (add, subtract, multiply, divide)
- Exception handling tests
- Multiple assertions in one test
- Nested test classes
- Disabled tests demonstration

## Documentation

For detailed information about:
- What is JUnit
- Uses of JUnit
- JUnit hooks and lifecycle methods
- How to execute tests
- JUnit reports
- Common Maven commands

Please refer to **[JUNIT_GUIDE.md](JUNIT_GUIDE.md)**

## Test Results

When you run `mvn test`, you should see:
- 10 tests run
- 0 failures
- 0 errors
- 1 skipped (intentionally disabled test)

## Example Test Output

```
=========================================
@BeforeAll: Setting up test environment
This runs ONCE before all tests
=========================================

--- @BeforeEach: Creating new Calculator instance ---
Test #1 is about to run
Executing testAddition()
Addition test passed: 5 + 3 = 8
--- @AfterEach: Cleaning up after test ---

...

=========================================
@AfterAll: Cleaning up test environment
Total tests executed: 8
This runs ONCE after all tests
=========================================
```

## Maven Commands Reference

| Command | Description |
|---------|-------------|
| `mvn clean` | Remove target directory |
| `mvn compile` | Compile main source code |
| `mvn test-compile` | Compile test source code |
| `mvn test` | Run all tests |
| `mvn test -Dtest=ClassName` | Run specific test class |
| `mvn test -Dtest=ClassName#methodName` | Run specific test method |
| `mvn package` | Compile, test, and package |
| `mvn install` | Install to local repository |

## License

This is a demonstration project for educational purposes.

---

**Happy Testing! 🧪**

