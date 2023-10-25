import java.io.Serializable;
import java.util.Scanner;

public class Menu implements Serializable {
    private Bank bank;
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.bank = new Bank();

        try {
            this.bank.loadDataFromFile();
        } catch (Exception var2) {
            System.out.println("File not found.");
        }

    }

    public void displayMenu() {
        System.out.println("---------Home Menu---------");
        System.out.println("1. Create bank account");
        System.out.println("2. View account details");
        System.out.println("3. Cash withdraw");
        System.out.println("4. Deposit");
        System.out.println("5. Transfer");
        System.out.println("6. Exit");
        System.out.println("Choose an option: ");
        int choice = this.scanner.nextInt();
        this.scanner.nextLine();
        switch (choice) {
            case 1:
                this.createAccount();
                break;
            case 2:
                this.viewAccountDetails();
                break;
            case 3:
                this.cashWithdraw();
                break;
            case 4:
                this.deposit();
                break;
            case 5:
                this.transfer();
                break;
            case 6:
                System.exit(0);
        }

    }

    public void createAccount() {
        System.out.println("Enter your details: ");
        System.out.println("- ID: ");
        String ID = this.scanner.nextLine();
        System.out.println("- first name: ");
        String firstName = this.scanner.nextLine();
        System.out.println("- last name: ");
        String lastName = this.scanner.nextLine();
        System.out.println("- occupation (1 - Student | 2 - Employee | 3 - Retired): ");
        int occupationNo = this.scanner.nextInt();
        Occupation occupation = null;
        switch (occupationNo) {
            case 1:
                occupation = Occupation.STUDENT;
                break;
            case 2:
                occupation = Occupation.EMPLOYEE;
                break;
            case 3:
                occupation = Occupation.RETIRED;
        }

        System.out.println("- age: ");
        int age = this.scanner.nextInt();
        System.out.println("- account type (1- Savings | 2 - Salary | 3 - Fixed Deposit): ");
        int accountTypeNo = this.scanner.nextInt();
        AccountType accountType = null;
        switch (accountTypeNo) {
            case 1:
                accountType = AccountType.SAVINGS;
                break;
            case 2:
                accountType = AccountType.SALARY;
                break;
            case 3:
                accountType = AccountType.FIXED_DEPOSIT;
        }

        try {
            int accountNo = (int)(Math.random() * 99.0 + 1.0);
            this.bank.createAccount(new User(ID, firstName, lastName, occupation, age), (int)(Math.random() * 99.0 + 1.0), accountType);
            System.out.println("Account number " + accountNo + " created successfully.");
        } catch (Exception var10) {
            System.out.println("Account could not be created.");
        }

    }

    public void viewAccountDetails() {
        System.out.println("Enter your account number: ");
        int accountNo = this.scanner.nextInt();
        Account account = this.bank.getAccountByNumber(accountNo);
        if (account != null) {
            account.viewAccountDetails();
        } else {
            System.out.println("Account not found.");
        }

    }

    public void cashWithdraw() {
        System.out.println("Enter your account number: ");
        int accountNo = this.scanner.nextInt();
        System.out.println("Enter the amount you want to withdraw: ");
        double amount = this.scanner.nextDouble();
        Account account = this.bank.getAccountByNumber(accountNo);
        if (account != null) {
            account.withdrawal(amount);
        } else {
            System.out.println("Account not found.");
        }

    }

    public void deposit() {
        System.out.println("Enter your account number: ");
        int accountNo = this.scanner.nextInt();
        System.out.println("Enter the amount you want to deposit: ");
        double amount = this.scanner.nextDouble();
        Account account = this.bank.getAccountByNumber(accountNo);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }

    }

    public void transfer() {
        System.out.println("Enter your account number: ");
        int accountNo = this.scanner.nextInt();
        System.out.println("Enter the account number to which you want to transfer the money: ");
        int recipientAccountNo = this.scanner.nextInt();
        System.out.println("Enter the amount you want to transfer: ");
        double amount = this.scanner.nextDouble();
        Account recipient = this.bank.getAccountByNumber(recipientAccountNo);
        Account account = this.bank.getAccountByNumber(accountNo);
        if (account != null) {
            account.transfer(recipient, amount);
        } else {
            System.out.println("Account not found.");
        }

    }
}

