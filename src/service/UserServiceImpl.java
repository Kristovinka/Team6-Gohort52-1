package service;

import model.Role;
import model.Transaction;
import model.User;
import repository.UserRepository;
import utils.EmailValidateException;
import utils.PasswordValidateException;
import utils.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Логики работы с пользователем

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password, Role role) throws EmailValidateException, PasswordValidateException {
                 // Проверяем валидность email и пароля
        UserValidator.isEmailValid(email);
        UserValidator.isPasswordValid(password);

                // Проверяем наличие пользователя с таким email
        if (userRepository.doesEmailExist(email)) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }

        // Создаем нового пользователя
        return userRepository.addUser(email, password, role);
    }

    @Override
    public boolean doesEmailExist(String email) {
        return userRepository.doesEmailExist(email);
    }

    @Override
    public List<Transaction> getUserTransactions(int userId) {
        if (userRepository.getUserById(userId) == null) {
            throw new IllegalArgumentException("Пользователь с таким id не существует.");
        }
        User user = userRepository.getUserById(userId);
        return user != null ? userRepository.getTransactionsByUser(user) : new ArrayList<>();
    }

    @Override
    public void clearAllUsers() {
        userRepository.clearAllUsers();
    }

    @Override
    public List<User> getAllUsers() {
        Map<Integer,User> map = userRepository.getAllUsers();

        List<User> allUsers = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : map.entrySet()) {
            User user = entry.getValue();
            allUsers.add(user);
        }
        return allUsers;

    }
}