package navaneeth;

/**
 * CurrentAccount class demonstrating INHERITANCE and POLYMORPHISM
 * - Extends BankAccount
 * - Overrides calculateInterest with different implementation
 */
public class CurrentAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.02; // 2% interest rate (lower than savings)
    private double overdraftLimit;
    
    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }
    
    // Method Overriding - POLYMORPHISM (different implementation than SavingsAccount)
    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
    
    // Override withdraw to allow overdraft
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
            double newBalance = getBalance() - amount;
            setBalance(newBalance); // Use protected setter
            if (newBalance < 0) {
                System.out.println("Overdraft used: " + Math.abs(newBalance));
            }
        } else {
            throw new IllegalArgumentException("Withdrawal exceeds available balance and overdraft limit");
        }
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}

