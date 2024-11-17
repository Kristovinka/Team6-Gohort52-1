package repository;

import model.Transaction;
import model.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public User addUser(String email, String password) {
        return null;
    }

    @Override
    public boolean doesEmailExist(String email) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public List<Transaction> getTransactionsByUser(User user) {
        return List.of();
    }

    @Override
    public void clearAllUsers() {

    }
}
