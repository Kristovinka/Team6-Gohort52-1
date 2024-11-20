package view;

import model.User;
import service.AccountService;
import service.CurrencyService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

class ConsoleMenu {
    private UserService userService;
    // discuss
    private AccountService accountService;
    // discuss
    private CurrencyService currencyService;
    private TransactionService transactionService;
    private Scanner scanner;

    public ConsoleMenu(UserService userService, AccountService accountService, CurrencyService currencyService, TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Вход как пользователь");
            System.out.println("3. Вход как администратор");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    loginAdmin();
                    break;
                case 4:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUserId(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("user"); // По умолчанию новый пользователь - обычный пользователь

        try {
            userService.registerUser(user);
            System.out.println("Регистрация успешна!");
        } catch (CustomException e) {
            System.err.println("Ошибка регистрации: " + e.getMessage());
        }
    }

    private void loginUser() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            User user = userService.login(email, password);
            System.out.println("Вход успешен!");
            UserMenu userMenu = new UserMenu(accountService, currencyService, transactionService);
            userMenu.showMenu(user);
        } catch (CustomException e) {
            System.err.println("Ошибка входа: " + e.getMessage());
        }
    }

    private void loginAdmin() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            User admin = userService.login(email, password);
            if (userService.isAdmin(admin)) {
                System.out.println("Вход успешен!");
                AdministratorMenu adminMenu = new AdministratorMenu(currencyService, transactionService, userService);
                adminMenu.showMenu(admin);
            } else {
                System.err.println("Вы не имеете прав администратора.");
            }
        } catch (CustomException e) {
            System.err.println("Ошибка входа: " + e.getMessage());
        }
    }
}
