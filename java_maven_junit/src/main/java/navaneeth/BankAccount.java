package navaneeth;

/**
 * BankAccount class demonstrating ENCAPSULATION
 * - Private fields with public getters/setters
 * - Data hiding and controlled access
 */
public class BankAccount {
    // Private fields - Encapsulation
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }
    
    // Public getters - Controlled access
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Protected method to allow subclasses to modify balance
    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    // Public methods - Business logic
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }
    
    // Method that can be overridden - demonstrates polymorphism
    public double calculateInterest() {
        return 0; // Base class returns 0
    }
    
    @Override
    public String toString() {
        return "Account: " + accountNumber + ", Holder: " + accountHolderName + ", Balance: " + balance;
    }
}

