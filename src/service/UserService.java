package service;

import model.Role;
import model.Transaction;
import model.User;
import utils.EmailValidateException;
import utils.PasswordValidateException;

import java.util.List;


            // Интерфейс для управления пользователями

public interface UserService {


    User registerUser(String email, String password, Role role) throws EmailValidateException, PasswordValidateException;


    boolean doesEmailExist(String email);


    List<Transaction> getUserTransactions(int userId);


    void clearAllUsers();


    List<User> getAllUsers();
}