package repository;

import model.User;

public interface UserRepository {

    User addUser(String email, String password);

    boolean doesEmailExist(String email);

    void clearAllUsers(); // Метод для удаления всех пользователей из хранилища
}
