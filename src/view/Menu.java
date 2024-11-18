package view;
import model.Account;
import model.User;

import java.util.Scanner;

public class Menu {
    private UserService userService;
    private AccountService accountService;
    private CurrencyService currencyService;
    private OperationService operationService;
    private Scanner scanner;

    public Menu(UserService userService, AccountService accountService, CurrencyService currencyService, OperationService operationService) {
        this.userService = userService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.operationService = operationService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
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
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("user"); // По умолчанию новый пользователь - обычный пользователь

        try {
            userService.registerUser(user);
            System.out.println("Регистрация успешна!");
        } catch (CustomException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
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
            if (userService.isAdmin(user)) {
                adminMenu(user);
            } else {
                userMenu(user);
            }
        } catch (CustomException e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("1. Просмотреть аккаунты");
            System.out.println("2. Пополнить аккаунт");
            System.out.println("3. Обмен валют");
            System.out.println("4. Просмотреть историю операций");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAccounts(user);
                    break;
                case 2:
                    depositAccount(user);
                    break;
                case 3:
                    exchangeCurrency(user);
                    break;
                case 4:
                    viewOperations(user);
                    break;
                case 5:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void adminMenu(User admin) {
        while (true) {
            System.out.println("1. Просмотреть аккаунты");
            System.out.println("2. Пополнить аккаунт");
            System.out.println("3. Обмен валют");
            System.out.println("4. Просмотреть историю операций");
            System.out.println("5. Изменить роль пользователя");
            System.out.println("6. Менять курс валюты");
            System.out.println("7. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAccounts(admin);
                    break;
                case 2:
                    depositAccount(admin);
                    break;
                case 3:
                    exchangeCurrency(admin);
                    break;
                case 4:
                    viewOperations(admin);
                    break;
                case 5:
                    changeUserRole();
                    break;
                case 6:
                    changeExchangeRate();
                    break;
                case 7:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void viewAccounts(User user) {
        System.out.println("Список аккаунтов пользователя " + user.getName() + ":");
        for (Account account : user.getAccounts().values()) {
            System.out.println("ID: " + account.getId() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getBalance());
        }
    }

    private void depositAccount(User user) {
        System.out.print("Введите ID аккаунта: ");
        String accountId = scanner.nextLine();
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        try {
            accountService.deposit(accountId, amount);
            System.out.println("Аккаунт успешно пополнен.");
        } catch (CustomException e) {
            System.out.println("Ошибка пополнения: " + e.getMessage());
        }
    }

    private void exchangeCurrency(User user) {
        System.out.print("Введите ID исходного аккаунта: ");
        String fromAccountId = scanner.nextLine();
        System.out.print("Введите ID целевого аккаунта: ");
        String toAccountId = scanner.nextLine();
        System.out.print("Введите сумму для обмена: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        try {
            currencyService.exchange(fromAccountId, toAccountId, amount);
            System.out.println("Обмен успешно выполнен.");
        } catch (CustomException e) {
            System.out.println("Ошибка обмена валют: " + e.getMessage());
        }
    }

    private void viewOperations(User user) {
        System.out.println("История операций пользователя " + user.getName() + ":");
        for (Operation operation : operationService.getOperationsByUser(user.getId())) {
            System.out.println("ID: " + operation.getId() + ", Тип: " + operation.getType() + ", Сумма: " + operation.getAmount() + ", Дата: " + operation.getDate());
        }
    }

    private void changeUserRole() {
        System.out.print("Введите ID пользователя: ");
        String userId = scanner.nextLine();
        System.out.print("Введите новую роль (user/admin): ");
        String newRole = scanner.nextLine();

        try {
            userService.changeUserRole(userId, newRole);
            System.out.println("Роль пользователя успешно изменена.");
        } catch (CustomException e) {
            System.out.println("Ошибка изменения роли: " + e.getMessage());
        }
    }

    private void changeExchangeRate() {
        System.out.print("Введите код исходной валюты: ");
        String fromCurrency = scanner.nextLine();
        System.out.print("Введите код целевой валюты: ");
        String toCurrency = scanner.nextLine();
        System.out.print("Введите новый курс: ");
        double rate = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        try {
            currencyService.updateExchangeRate(fromCurrency, toCurrency, rate);
            System.out.println("Курс валюты успешно изменен.");
        } catch (CustomException e) {
            System.out.println("Ошибка изменения курса: " + e.getMessage());
        }
    }
}
