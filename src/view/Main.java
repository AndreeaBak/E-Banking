package view;

import service.Bank;

public class Main {

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank("My Bank");
        Menu menu = new Menu(bank);

        //bank.writeAccount();
        bank.loadDataFromFile();
        menu.displayMenu();
        //bank.saveDataToFile();

    }
}