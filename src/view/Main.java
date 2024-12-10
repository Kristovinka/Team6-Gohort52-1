package view;

import repository.*;
import service.*;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);

        CurrencyRepository currencyRepository = new CurrencyRepoImpl();
        CurrencyService currencyService = new CurrencyServiceImpl(currencyRepository);

        AccountRepository accountRepository = new AccountRepoImpl();
        AccountService accountService = new AccountServiceImpl(accountRepository, currencyRepository, userRepository);

        TransactionRepository transactionRepository = new TransactionRepoImpl();
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, currencyRepository,accountRepository);

        ConsoleMenu consoleMenu = new ConsoleMenu(userService, accountService, currencyService, transactionService);
        consoleMenu.showMenu();
    }
}

