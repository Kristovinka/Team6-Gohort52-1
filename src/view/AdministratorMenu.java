package view;

import model.Transaction;
import model.User;
import service.CurrencyService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;
import java.util.List;

public class AdministratorMenu {
    private CurrencyService currencyService;
    private TransactionService transactionService;
    private UserService userService;
    private Scanner scanner;

    public AdministratorMenu(CurrencyService currencyService, TransactionService operationService, UserService userService) {
        this.currencyService = currencyService;
        this.transactionService = operationService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(User admin) {
        while (true) {
            System.out.println("1. Изменить курс валюты");
            System.out.println("2. Добавить валюту");
            System.out.println("3. Удалить валюту");
            System.out.println("4. Просмотр операций пользователя");
            System.out.println("5. Просмотр операций по валюте");
            System.out.println("6. Назначить пользователя администратором");
            System.out.println("7. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    changeExchangeRate();
                    break;
                case 2:
                    addCurrency();
                    break;
                case 3:
                    removeCurrency();
                    break;
                case 4:
                    viewUserOperations();
                    break;
                case 5:
                    viewCurrencyOperations();
                    break;
                case 6:
                    assignAdminRole();
                    break;
                case 7:
                    System.out.println("Выход...");
                    return;
                default:
                    System.err.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void changeExchangeRate() {
        System.out.print("Введите код исходной валюты: ");
        String fromCurrency = scanner.nextLine();
//        System.out.print("Введите код целевой валюты: ");
//        String toCurrency = scanner.nextLine();
        System.out.print("Введите новый курс: ");
        double rate = scanner.nextDouble();
        scanner.nextLine(); // consume newline

//        try {
            transactionService.updateExchangeRate(fromCurrency, rate);
            System.out.println("Курс валюты успешно изменен.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка изменения курса: " + e.getMessage());
//        }
    }

    private void addCurrency() {
        System.out.print("Введите код новой валюты: ");
        String code = scanner.nextLine();
        System.out.print("Введите название новой валюты: ");
        String name = scanner.nextLine();
        System.out.print("Введите курс новой валюты: ");
        double exchangeRate = scanner.nextDouble();
        scanner.nextLine();

        transactionService.addExchangeRate(code, name, exchangeRate);
        System.out.println("Новая валюта успешно добавлена.");
    }

    private void removeCurrency() {
        System.out.print("Введите код валюты для удаления: ");
        String code = scanner.nextLine();

//        try {
            transactionService.removeExchangeRate(code);
            System.out.println("Валюта успешно удалена.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка удаления валюты: " + e.getMessage());
//        }
    }

    private void viewUserOperations() {
        userService.getAllUsers();
        System.out.print("Введите ID пользователя: ");
        int userId = scanner.nextInt();

//        try {
            User user = userService.getUserById(userId);
        System.out.println("История операций пользователя \n" + user.getUserTransactions());

//            System.out.println("История операций пользователя " + user.getName() + ":");
//            for (Operation operation : transactionService.getOperationsByUser(userId)) {
//                System.out.println("ID: " + operation.getId() + ", Тип: " + operation.getType() + ", Сумма: " + operation.getAmount() + ", Дата: " + operation.getDate());
//            }
//        } catch (CustomException e) {
//            System.err.println("Ошибка просмотра операций пользователя: " + e.getMessage());
//        }
    }

    private void viewCurrencyOperations() {
        System.out.print("Введите код валюты: ");
        String currencyCode = scanner.nextLine();
//todo
        //List<Transaction> operations = currencyService.getOperationsByCurrency(currencyCode);
        System.out.println("История операций по валюте " + currencyCode + ":");
//        for (Transaction operation : operations) {
//            System.out.println("ID: " + operation.getId() + ", Тип: " + operation.getType() + ", Сумма: " + operation.getAmount() + ", Дата: " + operation.getDate());
//        }
    }

    private void assignAdminRole() {
        System.out.print("Введите ID пользователя: ");
        String userId = scanner.nextLine();
        System.out.print("Введите новую роль (admin/moderator/cashier): ");
        String newRole = scanner.nextLine();

//        try {
//            userService.changeUserRole(userId, newRole);
//            System.out.println("Роль пользователя успешно изменена.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка изменения роли: " + e.getMessage());
//        }
    }
}

