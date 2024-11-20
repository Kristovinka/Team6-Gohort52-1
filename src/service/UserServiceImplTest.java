package service;

import model.Role;
import model.Transaction;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import utils.EmailValidateException;
import utils.PasswordValidateException;
import utils.RegistrationValidateException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new InMemoryUserRepository();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testRegisterUser() throws EmailValidateException, PasswordValidateException, RegistrationValidateException {
        String email = "test@example.com";
        String password = "Password123!";
        Role role = Role.USER;

        User user = userService.registerUser(email, password);

        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testDoesEmailExist() throws RegistrationValidateException, EmailValidateException, PasswordValidateException {
        String email = "test@example.com";
        userService.registerUser(email, "Password123!");

        boolean exists = userService.doesEmailExist(email);

        assertTrue(exists);
    }

    @Test
    public void testGetUserTransactions() throws RegistrationValidateException, EmailValidateException, PasswordValidateException {
        User user = userService.registerUser("test@example.com", "Password123!");
        Transaction transaction1 = new Transaction(1, 100.0);
        Transaction transaction2 = new Transaction(2, 50.0);
        user.addTransaction(transaction1);
        user.addTransaction(transaction2);

        List<Transaction> transactions = userService.getUserTransactions(user.getUserId());

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals(100.0, transactions.get(0).getAmount());
    }

    // Реализация InMemoryUserRepository для тестирования
    private class InMemoryUserRepository implements UserRepository {
        private List<User> users = new ArrayList<>();
        private int currentId = 1;

        @Override
        public User save(User user) {
            user.setUserId(currentId++);
            users.add(user);
            return user;
        }

        @Override
        public User addUser(String email, String password, Role role) {
            return null;
        }

        @Override
        public boolean doesEmailExist(String email) {
            return users.stream().anyMatch(u -> u.getEmail().equals(email));
        }

        @Override
        public Map<Integer, User> getAllUsers() {
            return Map.of();
        }

        @Override
        public List<Transaction> getTransactionsByUser(User user) {
            return List.of();
        }

        @Override
        public User getUserById(int userID) {
            return null;
        }

        @Override
        public void clearAllUsers() {

        }

        @Override
        public User findById(int userId) {
            return users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
        }

        @Override
        public List<User> findAll() {
            return new ArrayList<>(users);
        }

        @Override
        public void deleteAll() {
            users.clear();
        }
    }
}
