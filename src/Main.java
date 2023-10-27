public class Main {

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank("My Bank");
        Menu menu = new Menu(bank);

        bank.loadDataFromFile();


            menu.displayMenu();
            bank.saveDataToFile();

    }
}