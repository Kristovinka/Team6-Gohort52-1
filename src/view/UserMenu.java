package view;

import model.Account;
import model.User;
import service.AccountService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

public class UserMenu {
    // discuss
    private AccountService accountService;
    // discuss
    private CurrencyService currencyService;
    //TransactionService -
    private TransactionService transactionService;
    private Scanner scanner;

    public UserMenu(AccountService accountService, CurrencyService currencyService, TransactionService transactionService) {
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public UserMenu(AccountService accountService, UserService userService) {

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
        System.out.println("Список аккаунтов пользователя " + user.getUserId() + ":");
        for (Account account : user.getAccountId().values()) {
            System.out.println("ID: " + account.getUserId() + ", Валюта: " + account.getCurrency() + ", Баланс: " + account.getBalance());
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
            System.err.println("Ошибка пополнения: " + e.getMessage());
        }
    }


    private void withdrawAccount(User user) {
        System.out.print("Введите ID аккаунта: ");
        String accountId = scanner.nextLine();
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        try {
            accountService.withdraw(accountId, amount);
            System.out.println("Средства успешно сняты.");
        } catch (CustomException e) {
            System.err.println("Ошибка снятия: " + e.getMessage());
        }
    }

    private void openAccount(User user) {
        System.out.print("Введите валюту для нового счета: ");
        String currency = scanner.nextLine();

        accountService.openAccount(user.getId(), currency);
        System.out.println("Новый счет успешно открыт.");
    }

    private void closeAccount(User user) {
        System.out.print("Введите ID аккаунта для закрытия: ");
        String accountId = scanner.nextLine();

        try {
            accountService.closeAccount(accountId);
            System.out.println("Счет успешно закрыт.");
        } catch (CustomException e) {
            System.err.println("Ошибка закрытия счета: " + e.getMessage());
        }
    }

    private void viewOperations(User user) {
        System.out.println("История операций пользователя " + user.getName() + ":");
        for (Operation operation : transactionService.getOperationsByUser(user.getId())) {
            System.out.println("ID: " + operation.getId() + ", Тип: " + operation.getType() + ", Сумма: " + operation.getAmount() + ", Дата: " + operation.getDate());
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
            System.err.println("Ошибка обмена валют: " + e.getMessage());
        }
    }
}

