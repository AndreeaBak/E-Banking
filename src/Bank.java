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

    public Bank() {
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public Account getAccountByNumber(int accountNo) {
        Iterator var2 = this.accounts.iterator();

        Account account;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            account = (Account)var2.next();
        } while(account.getAccountNo() != accountNo);

        return account;
    }

    public void createAccount(User user, int accountNo, AccountType accountType) throws Exception {
        Account newAccount = new Account(user, accountNo, accountType);
        this.accounts.add(newAccount);
        this.saveDataToFile();
    }

    public void writeAccount() throws Exception {
        Account account1 = new Account(new User("6010629460131", "Andreea", "Bak", Occupation.STUDENT, 22), 1, AccountType.SALARY);
        Account account2 = new Account(new User("1890111418262", "Mihai", "Radu", Occupation.EMPLOYEE, 34), 2, AccountType.SALARY);
        Account account3 = new Account(new User("2970426416322", "Iulia", "Staicu", Occupation.STUDENT, 25), 3, AccountType.FIXED_DEPOSIT);
        Account account4 = new Account(new User("6070629456024", "Maria", "Dobre", Occupation.STUDENT, 16), 4, AccountType.SALARY);
        Account account5 = new Account(new User("1600718417928", "Alexandru", "Badoiu", Occupation.RETIRED, 63), 5, AccountType.FIXED_DEPOSIT);
        List<Account> accountList = new ArrayList();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
        accountList.add(account5);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("accountsData", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    public void saveDataToFile() throws Exception {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("accountsData", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.accounts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void loadDataFromFile() throws Exception {
        new ArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("accountsData");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Account> accountList = (ArrayList)objectInputStream.readObject();
            this.accounts = accountList;
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        } catch (ClassNotFoundException var5) {
            throw new RuntimeException(var5);
        }
    }

    public void deleteAccount(int accountNo) throws Exception {
        this.accounts.removeIf((account) -> {
            return account.getAccountNo() == accountNo;
        });
        this.saveDataToFile();
    }

    public String toString() {
        return "Bank{name='" + this.name + "', accounts=" + this.accounts + "}";
    }
}

