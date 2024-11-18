package view;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CurrencyService currencyService = new CurrencyService();
        OperationService operationService = new OperationService();

        ConsoleMenu consoleMenu = new ConsoleMenu(userService, accountService, currencyService, operationService);
        consoleMenu.showMenu();
    }
}

