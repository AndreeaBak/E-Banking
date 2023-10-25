import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private User user;
    private int accountNo;
    private AccountType accountType;
    private double balance;
    private List<Transaction> transactions;

    public Account(User user, int accountNo, AccountType accountType) {
        this.user = user;
        this.accountNo = accountNo;
        if (user.getAge() < 18) {
            this.accountType = AccountType.SAVINGS;
        } else {
            this.accountType = accountType;
        }

        this.balance = 0.0;
        this.transactions = new ArrayList();
    }

    public int getAccountNo() {
        return this.accountNo;
    }

    public User getUser() {
        return this.user;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void deposit(double amount) {
        if (amount > 0.0) {
            this.balance += amount;
            Transaction transaction = new Transaction("Deposit", amount);
            this.transactions.add(transaction);
        } else {
            System.out.println("Invalid amount.");
        }

    }

    public boolean withdrawal(double amount) {
        if (amount > 0.0 && amount <= this.balance && this.accountType == AccountType.SALARY) {
            this.balance -= amount;
            Transaction transaction = new Transaction("Withdrawal", amount);
            this.transactions.add(transaction);
            return true;
        } else {
            System.out.println("Transaction denied. Check your balance and your account type.");
            return false;
        }
    }

    public void transfer(Account recipient, double amount) {
        if (this.withdrawal(amount)) {
            recipient.deposit(amount);
            Transaction transaction = new Transaction("Transfer", amount);
            this.transactions.add(transaction);
        } else {
            System.out.println("Insufficient funds.");
        }

    }

    public void viewAccountDetails() {
        System.out.println("Balance: " + this.getBalance());
        System.out.println("Account type: " + this.getAccountType());
        System.out.println("Transaction history: " + this.getTransactions());
    }
}