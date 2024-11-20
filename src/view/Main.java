package view;

import model.User;
import repository.AccountRepoImpl;
import repository.AccountRepository;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.AccountService;
import service.AccountServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        AccountRepository accountRepository = new AccountRepoImpl();
        AccountService accountService = new AccountServiceImpl(accountRepository);

        UserMenu userMenu = new UserMenu(accountService, userService);

        // Пример пользователя для тестирования
        try {
            User user = userService.registerUser("test@example.com", "password"); // или как у тебя реализован метод login
            userMenu.showMenu(user);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

