package view;

import model.User;
import service.AccountService;
import service.CurrencyService;
import service.TransactionService;
import service.UserService;
import utils.EmailValidateException;
import utils.PasswordValidateException;
import utils.RegistrationValidateException;
import utils.RoleValidateException;

import java.util.Scanner;

class ConsoleMenu {
    private final UserService userService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    private final TransactionService transactionService;
    private final Scanner scanner;

    public ConsoleMenu(UserService userService, AccountService accountService,
                       CurrencyService currencyService, TransactionService transactionService) {
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
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            System.out.println("Регистрация успешна!" );
            System.out.println("Новый пользователь: " + userService.registerUser(email, password));
        } catch (RegistrationValidateException e) {
            System.out.println(e.getMessage());

        } catch (EmailValidateException e) {
            System.out.println(e.getMessage());

        } catch (PasswordValidateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loginUser() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            userService.loginUser(email, password);
            System.out.println("Вход успешен!");

            UserMenu userMenu = new UserMenu( userService, accountService, currencyService, transactionService);
            userMenu.showMenu(userService.getUserByEmail(email));
        } catch (EmailValidateException e) {
            System.err.println("Ошибка входа: " + e.getMessage());
        } catch (PasswordValidateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loginAdmin() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

//        try {
//            userService.loginAdmin(email, password);
//            if (userService.isAdmin(admin)) {
//                System.out.println("Вход успешен!");
//                AdministratorMenu adminMenu = new AdministratorMenu(currencyService, transactionService, userService);
//                adminMenu.showMenu(admin);
//            } else {
//                System.err.println("Вы не имеете прав администратора.");
//            }
//        } catch (EmailValidateException e) {
//            System.err.println("Ошибка входа: " + e.getMessage());
//        } catch (PasswordValidateException e) {
//            System.out.println(e.getMessage());
//        } catch (RoleValidateException e) {
//            System.err.println("Ошибка входа: " + e.getMessage());
//        }
    }
}
