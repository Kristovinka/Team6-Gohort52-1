package view;

import model.Account;
import model.User;
import service.AccountService;
import service.CurrencyService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

public class UserMenu {
    private final UserService userService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    private final TransactionService transactionService;
    private final Scanner scanner;

    public UserMenu(UserService userService, AccountService accountService,
                    CurrencyService currencyService, TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(User user) {
        while (true) {
            System.out.println("1. Просмотреть баланс");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять средства со счета");
            System.out.println("4. Открыть новый счет");
            System.out.println("5. Закрыть счет");
            System.out.println("6. Просмотреть историю операций");
            System.out.println("7. Обмен валют");
            System.out.println("8. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewBalance(user);
                    break;
                case 2:
                    depositAccount(user);
                    break;
                case 3:
                    withdrawAccount(user);
                    break;
                case 4:
                    openAccount(user);
                    break;
                case 5:
                    closeAccount(user);
                    break;
                case 6:
                    viewOperations(user);
                    break;
                case 7:
                    exchangeCurrency(user);
                    break;
                case 8:
                    System.out.println("Выход...");
                    return;
                default:
                    System.err.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void viewBalance(User user) {
        System.out.println("Список аккаунтов пользователя " + user.getEmail() + ":");
        accountService.getAccountsByUser_id(user.getUserId());
//        for (Account account : user.getAccounts().values()) {
//            System.out.println("ID: " + account.getId() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getBalance());
//        }
    }

    private void depositAccount(User user) {
        System.out.println( accountService.getAccountsByUser_id(user.getUserId()));
        System.out.print("Введите ID аккаунта: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

//        try {
            transactionService.putMoney(accountId, amount);
            System.out.println("Аккаунт успешно пополнен.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка пополнения: " + e.getMessage());
//        }
    }

    private void withdrawAccount(User user) {
        System.out.println( accountService.getAccountsByUser_id(user.getUserId()));
        System.out.print("Введите ID аккаунта: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

//        try {
            transactionService.withdrawMoney(accountId, amount);
            System.out.println("Средства успешно сняты.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка снятия: " + e.getMessage());
//        }
    }

    private void openAccount(User user) {
        System.out.print("Введите валюту для нового счета: ");
        String currency = scanner.nextLine();

        accountService.addAccount(currency, user.getUserId());
        System.out.println("Новый счет успешно открыт.");
    }

    private void closeAccount(User user) {
        System.out.println( accountService.getAccountsByUser_id(user.getUserId()));
        System.out.print("Введите ID аккаунта для закрытия: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

//        try {
            accountService.deleteAccount(accountId);
            System.out.println("Счет успешно закрыт.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка закрытия счета: " + e.getMessage());
//        }
    }

    private void viewOperations(User user) {
        System.out.println("История операций пользователя ");
        System.out.println(user.getUserTransactions());
//        System.out.println("История операций пользователя " + user.getName() + ":");
//        for (Operation operation : operationService.getOperationsByUser(user.getId())) {
//            System.out.println("ID: " + operation.getId() + ", Тип: " + operation.getType() + ", Сумма: " + operation.getAmount() + ", Дата: " + operation.getDate());
//        }
    }

    private void exchangeCurrency(User user) {
        System.out.println( accountService.getAccountsByUser_id(user.getUserId()));
        System.out.print("Введите ID исходного аккаунта: ");
        int fromAccountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите ID целевого аккаунта: ");
        int toAccountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите сумму для обмена: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

//        try {
            transactionService.exchangeMoney(fromAccountId, toAccountId, amount);
            System.out.println("Обмен успешно выполнен.");
//        } catch (CustomException e) {
//            System.err.println("Ошибка обмена валют: " + e.getMessage());
//        }
    }
}

