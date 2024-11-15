package repository;

import model.User;

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
    public void clearAllUsers() {

    }
}
