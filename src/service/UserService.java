package service;

import model.Transaction;
import model.User;
import utils.EmailValidateException;
import utils.PasswordValidateException;
import utils.RegistrationValidateException;

import java.util.List;


            // Интерфейс для управления пользователями

public interface UserService {


    User registerUser(String email, String password) throws EmailValidateException, PasswordValidateException, RegistrationValidateException;


    boolean doesEmailExist(String email);


    List<Transaction> getUserTransactions(int userId) throws RegistrationValidateException;


    void clearAllUsers();


    List<User> getAllUsers();
}