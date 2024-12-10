package service;

import model.Role;
import model.Transaction;
import model.User;
import repository.UserRepository;
import utils.EmailValidateException;
import utils.PasswordValidateException;
import utils.RegistrationValidateException;
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
    public User registerUser(String email, String password) throws EmailValidateException, PasswordValidateException, RegistrationValidateException{
                 // Проверяем валидность email и пароля
        UserValidator.isEmailValid(email);
        UserValidator.isPasswordValid(password);

                // Проверяем наличие пользователя с таким email
        if (userRepository.doesEmailExist(email)) {
            throw new RegistrationValidateException("Пользователь с таким email уже существует.");
        }

        // Создаем нового пользователя
        return userRepository.addUser(email, password, Role.USER);
    }

    @Override
    public void loginUser(String email, String password) throws EmailValidateException, PasswordValidateException {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new EmailValidateException("Неверный адрес электронной почты!");
        }

        if (!user.getPassword().equals(password)) {
            throw new PasswordValidateException("Неверный пароль!");
        }

    }

    @Override
    public boolean doesEmailExist(String email) {
        return userRepository.doesEmailExist(email);
    }

    @Override
    public List<Transaction> getUserTransactions(int userId) throws RegistrationValidateException{
        if (userRepository.getUserById(userId) == null) {
            throw new RegistrationValidateException("Пользователь с таким id не существует.");
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
        if(map.isEmpty()) return null;

        return (List<User>) map.values();
    }

    @Override
    public User getUserById(int userID) {
        if ( userRepository.getUserById(userID) == null) System.out.println("Пользователь с таким id ne существует");
        return userRepository.getUserById(userID);
    }

    @Override
    public User getUserByEmail(String email) throws EmailValidateException {
        if (userRepository.getUserByEmail(email) == null) {
            throw new EmailValidateException("Пользователь с таким email ne существует.");
        }
        return userRepository.getUserByEmail(email);
    }
}