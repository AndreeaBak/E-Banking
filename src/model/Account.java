package model;

import enums.AccountType;
import exceptions.CustomExceptions;

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
        this.accountType = AccountType.SAVINGS;
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

    public boolean withdrawal(double amount) throws CustomExceptions.InsufficientFundsException {
        if (amount > 0.0 && amount <= this.balance) {
            this.balance -= amount;
            Transaction transaction = new Transaction("Withdrawal", amount);
            this.transactions.add(transaction);
            return true;
        } else {
            throw new CustomExceptions.InsufficientFundsException("Insufficient Funds.");
        }
    }

    public void transfer(Account recipient, double amount) throws CustomExceptions.InsufficientFundsException {
        if (this.withdrawal(amount)) {
            recipient.deposit(amount);
            Transaction transaction = new Transaction("Transfer", amount);
            this.transactions.add(transaction);
        }
    }

    public void viewAccountDetails() {
        System.out.println("Account Number: " + this.getAccountNo());
        System.out.println("Balance: " + this.getBalance());
        if(this.getTransactions() != null){
            System.out.println("Transaction history: " + this.getTransactions());
        } else {
            System.out.println("Transaction history: No transaction.");
        }

        System.out.println("Account type: " + this.getAccountType());
    }
}