import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bank implements Serializable {
    private String name;
    private List<Account> accounts = new ArrayList();

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public Account getAccountByNumber(int accountNo) {
       for(Account account: accounts){
           if(account.getAccountNo() == accountNo){
               return account;
           }
       }
       return null;
    }

    public void createAccount(User user, int accountNo, AccountType accountType) {
        Account newAccount = new Account(user, accountNo, accountType);
        accounts.add(newAccount);
        saveDataToFile();
    }

    public void deleteAccount(int accountNo) {
        accounts.removeIf(account -> account.getAccountNo() == accountNo);
        saveDataToFile();
    }

    public void writeAccount() throws Exception {
        Account account1 = new Account(new User("6010629460131", "Andreea", "Bak", Occupation.STUDENT, 22), 1, AccountType.SALARY);
        Account account2 = new Account(new User("1890111418262", "Mihai", "Radu", Occupation.EMPLOYEE, 34), 2, AccountType.SALARY);
        Account account3 = new Account(new User("2970426416322", "Iulia", "Staicu", Occupation.STUDENT, 25), 3, AccountType.FIXED_DEPOSIT);
        Account account4 = new Account(new User("6070629456024", "Maria", "Dobre", Occupation.STUDENT, 16), 4, AccountType.SALARY);
        Account account5 = new Account(new User("1600718417928", "Alexandru", "Badoiu", Occupation.RETIRED, 63), 5, AccountType.FIXED_DEPOSIT);
        //List<Account> accountList = new ArrayList();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("accountsData", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accounts);
            System.out.println("Success");
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    public void saveDataToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("accountsData");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(accounts);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("accountsData");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            List<Account> accountList = (ArrayList<Account>) objectInputStream.readObject();
            accounts = accountList;
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found or error loading data.");
        }
    }

    public String toString() {
        return "Bank{name='" + this.name + "', accounts=" + this.accounts + "}";
    }
}

