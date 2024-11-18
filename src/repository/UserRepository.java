package repository;

import model.Role;
import model.Transaction;
import model.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    User addUser(String email, String password, Role role);

    boolean doesEmailExist(String email);

    Map<Integer,User> getAllUsers(); // Метод для получения всех пользователей

    List<Transaction> getTransactionsByUser(User user); // Метод позволят получать операции пользователя


    void clearAllUsers(); // Метод для удаления всех пользователей из хранилища
}
