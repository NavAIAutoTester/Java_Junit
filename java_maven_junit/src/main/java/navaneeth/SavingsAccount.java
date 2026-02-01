package navaneeth;

/**
 * SavingsAccount class demonstrating INHERITANCE
 * - Extends BankAccount (inherits all fields and methods)
 * - Overrides methods for specific behavior (POLYMORPHISM)
 */
public class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.05; // 5% interest rate
    
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }
    
    // Method Overriding - POLYMORPHISM
    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
    
    // Additional method specific to SavingsAccount
    public void addInterest() {
        double interest = calculateInterest();
        deposit(interest);
    }
}

