package repository;

import model.Role;
import model.Transaction;
import model.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository{

    private final Map<Integer, User> users;
    private AtomicInteger usersCount = new AtomicInteger(1);

    public UserRepositoryImpl() {
        users = new LinkedHashMap<Integer, User>();
        addUsers();
    }

    private void addUsers() {
        User admin = new User("1", "1", Role.ADMIN);
        admin.setRole(Role.ADMIN);

        User blocked = new User("2", "2", Role.ADMIN);
        blocked.setRole(Role.BLOCKED);
        users.put(usersCount.getAndIncrement(), admin);
        users.put(usersCount.getAndIncrement(),blocked);
        users.put(usersCount.getAndIncrement(),new User("user@email.com", "qwerty1Q!", Role.ADMIN));
    }

    @Override
    public User addUser(String email, String password, Role role) {
        User user = new User(email, password, role);
        users.put(usersCount.getAndIncrement(),user);
        return user;
    }

    @Override
    public boolean doesEmailExist(String email) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (entry.getValue().getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public Map<Integer,User> getAllUsers() {
        return users;
    }

    @Override
    public List<Transaction> getTransactionsByUser(User user) {
        return user.getUserTransactions();
    }

    @Override
    public void clearAllUsers() {
        users.clear();
    }
}
