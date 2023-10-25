public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        User user = new User("123", "Andreea", "Bak", Occupation.STUDENT, 22);
        User user2 = new User("456", "Mike", "Ubu", Occupation.EMPLOYEE, 45);
        Account account = new Account(user, 1, AccountType.SALARY);
        account.deposit(1000.0);
        account.viewAccountDetails();
        System.out.println("-----------");
        Account recipient = new Account(user2, 2, AccountType.SALARY);
        account.transfer(recipient, 200.0);
        account.viewAccountDetails();
        System.out.println("-----------");
        recipient.viewAccountDetails();
        System.out.println("-----------");
        recipient.withdrawal(100.0);
        recipient.viewAccountDetails();
        System.out.println("-----------");
    }
}