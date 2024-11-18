package repository;

import model.Transaction;
import model.User;

import java.util.List;

public interface UserRepository {

    User addUser(String email, String password);

    boolean doesEmailExist(String email);

    List<User> getAllUsers(); // Метод для получения всех пользователей

    List<Transaction> getTransactionsByUser(User user); // Метод позволят получать операции пользователя


    void clearAllUsers(); // Метод для удаления всех пользователей из хранилища
}
