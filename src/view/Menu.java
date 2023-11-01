package view;

import enums.AccountType;
import enums.Occupation;
import exceptions.CustomExceptions;
import model.Account;
import model.User;
import service.Bank;
import service.Statistics;

import java.io.Serializable;
import java.util.Scanner;

public class Menu implements Serializable{
    private Bank bank;
    private Scanner scanner;

    public Menu(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("---------E-Banking Menu---------");
        System.out.println("1. Create account");
        System.out.println("2. View account details");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Delete account");
        System.out.println("7. Statistics");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 0:
                viewAccounts();
                break;
            case 1:
                createAccount();
                break;
            case 2:
                viewAccountDetails();
                break;
            case 3:
                deposit();
                break;
            case 4:
                withdraw();
                break;
            case 5:
                transfer();
                break;
            case 6:
                deleteAccount();
                break;
            case 7:
                displayStatisticsMenu();
                break;
            case 8:
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayStatisticsMenu() {
        Statistics statistics = new Statistics(this.bank.getAccounts());
        System.out.println("Select an option: ");
        System.out.println("1. Average Balance");
        System.out.println("2. Number of accounts by type");
        System.out.println("3. Number of users by occupation");
        System.out.println("4. Number of users by age range");
        System.out.println("5. Back");
        int response = scanner.nextInt();

        switch (response){
            case 1:
                System.out.printf("Average Balance: %.2f\n", statistics.calculateAverageBalance());
                break;
            case 2:
                System.out.println("Please enter an account type (1 - SAVINGS | 2 - SALARY | 3 - FIXED_DEPOSIT):");
                int accountTypeNo = scanner.nextInt();
                switch (accountTypeNo){
                    case 1:
                        System.out.println("Number of savings accounts: "+statistics.accountsByType(AccountType.SAVINGS));
                        break;
                    case 2:
                        System.out.println("Number of salary accounts: "+statistics.accountsByType(AccountType.SALARY));
                        break;
                    case 3:
                        System.out.println("Number of fixed-deposit account: "+statistics.accountsByType(AccountType.FIXED_DEPOSIT));
                        break;
                }
                break;
            case 3:
                System.out.println("Please enter an option (1 - STUDENT | 2 - EMPLOYEE | 3 - RETIRED):");
                int occupationNo = scanner.nextInt();
                switch (occupationNo){
                    case 1:
                        System.out.println("Number of students: "+statistics.countUsersByOccupation(Occupation.STUDENT));
                        break;
                    case 2:
                        System.out.println("Number of employees: "+statistics.countUsersByOccupation(Occupation.EMPLOYEE));
                        break;
                    case 3:
                        System.out.println("Number of retirees: "+statistics.countUsersByOccupation(Occupation.RETIRED));
                        break;
                }
                break;
            case 4:
                System.out.println("Please enter the age range:");
                int minAge = scanner.nextInt();
                int maxAge = scanner.nextInt();
                if(minAge > maxAge){
                    int aux = maxAge;
                    maxAge = minAge;
                    minAge = aux;
                }
                System.out.println("Number of users between "+minAge+" and "+maxAge+" years:"+statistics.countUsersByAgeRange(minAge, maxAge));
                break;
            case 5:
                displayMenu();
                break;
        }

        backStatistics();
    }

    public void backMenu(){
        System.out.println("Press 0 to go back...");
        int response = scanner.nextInt();
        if(response == 0){
            displayMenu();
        } else {
            System.out.println("Invalid choice.");
            backMenu();
        }
    }

    public void backStatistics(){
        System.out.println("Press 0 to select another option or press 1 to go back to main menu...");
        int response = scanner.nextInt();
        if(response == 0){
            displayStatisticsMenu();
        } else if (response == 1) {
            displayMenu();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void createAccount() {
        System.out.println("Enter user details:");
        System.out.print("ID: ");
        String ID = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Occupation (1 - Student, 2 - Employee, 3 - Retired): ");
        int occupationChoice = scanner.nextInt();
        Occupation occupation = null;
        switch (occupationChoice) {
            case 1:
                occupation = Occupation.STUDENT;
                break;
            case 2:
                occupation = Occupation.EMPLOYEE;
                break;
            case 3:
                occupation = Occupation.RETIRED;
                break;
            default:
                System.out.println("Invalid occupation choice.");
                return;
        }
        System.out.print("Age: ");
        int age = scanner.nextInt();

        int accountNo;
        do {
            accountNo =  (int)(Math.random() * 99.0 + 1.0);
        } while(isAccountNoTaken(accountNo));

        System.out.print("Account Type (1 - Savings, 2 - Salary, 3 - Fixed Deposit): ");
        int accountTypeChoice = scanner.nextInt();
        AccountType accountType = null;
        switch (accountTypeChoice) {
            case 1:
                accountType = AccountType.SAVINGS;
                break;
            case 2:
                accountType = AccountType.SALARY;
                break;
            case 3:
                accountType = AccountType.FIXED_DEPOSIT;
                break;
            default:
                System.out.println("Invalid account type choice.");
                return;
        }

        User user = null;
        try {
            user = new User(ID, firstName, lastName, occupation, age);
            bank.createAccount(user, accountNo, accountType);
            System.out.println("Account No. " +accountNo+" created successfully.");
        } catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }

        backMenu();
    }

    private boolean isAccountNoTaken(int accountNo){
        for(Account account: bank.getAccounts()) {
            if (account.getAccountNo() == accountNo)
                return true;
        }
        return false;
    }
    public void viewAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        try {
            Account account = bank.getAccountByNumber(accountNo);
            account.viewAccountDetails();
        } catch (CustomExceptions.AccountNotFoundException e){
            System.out.println(e.getMessage());
        }
        backMenu();
    }

    public void deposit() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        Account account = null;
        try {
            account = bank.getAccountByNumber(accountNo);
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful.");
            bank.saveDataToFile();
        } catch (CustomExceptions.AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        backMenu();
    }

    public void withdraw() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        try {
            Account account = bank.getAccountByNumber(accountNo);
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (account.withdrawal(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Withdrawal failed.");
            }
            bank.saveDataToFile();
        } catch (CustomExceptions.AccountNotFoundException | CustomExceptions.InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        backMenu();
    }

    public void transfer() {
        System.out.print("Enter Account Number: ");
        int senderAccountNo = scanner.nextInt();
        try {
            Account senderAccount = bank.getAccountByNumber(senderAccountNo);
            System.out.print("Enter recipient Account Number: ");
            int recipientAccountNo = scanner.nextInt();
            Account recipientAccount = bank.getAccountByNumber(recipientAccountNo);
            if (recipientAccount != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                senderAccount.transfer(recipientAccount, amount);
                System.out.println("Transfer successful.");
                bank.saveDataToFile();
            } else {
                System.out.println("Recipient account not found.");
            }

        } catch (CustomExceptions.AccountNotFoundException | CustomExceptions.InsufficientFundsException e){
            System.out.println(e.getMessage());
        }

        backMenu();
    }

    public void deleteAccount() {
        System.out.print("Enter Account Number: ");
        int accountNo = scanner.nextInt();
        try {
            bank.getAccountByNumber(accountNo);
            bank.deleteAccount(accountNo);
            System.out.println("Account deleted.");
        } catch (CustomExceptions.AccountNotFoundException e){
            System.out.println(e.getMessage());
        }

        backMenu();
    }

    public void viewAccounts(){
        System.out.println("Accounts: ");
        for(Account account: bank.getAccounts()){
            System.out.println(account.getAccountNo());
        }
        backMenu();
    }
}

