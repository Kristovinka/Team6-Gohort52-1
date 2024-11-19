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
        User newUser = new User(email, password, role);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public boolean doesEmailExist(String email) {
        return userRepository.doesEmailExist(email);
    }

    @Override
    public List<Transaction> getUserTransactions(int userId) {
        User user = userRepository.findById(userId);
        return user != null ? user.getTransactions() : new ArrayList<>();
    }

    @Override
    public void clearAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}