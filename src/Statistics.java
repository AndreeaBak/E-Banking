import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Account> accounts = new ArrayList<>();

    public Statistics(List<Account> accounts){
        this.accounts = accounts;
    }

    public double calculateAverageBalance(){
        double totalBalance = 0;
        for(Account account: accounts){
            totalBalance += account.getBalance();
        }
        return totalBalance/accounts.size();
    }

    public int accountsByType(AccountType accountType){
        int count=0;
        for(Account account: accounts){
            if(account.getAccountType().equals(accountType)){
                count++;
            }
        }
        return count;
    }

    public int countUsersByOccupation(Occupation occupation) {
        int count = 0;
        for (Account account : accounts) {
            if (account.getUser().getOccupation() == occupation) {
                count++;
            }
        }
        return count;
    }

    public int countUsersByAgeRange(int minAge, int maxAge) {
        int count = 0;
        for (Account account : accounts) {
            int userAge = account.getUser().getAge();
            if (userAge >= minAge && userAge <= maxAge) {
                count++;
            }
        }
        return count;
    }
}
