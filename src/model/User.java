package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class User {
    private String email;
    private String password;
    Map<Integer, User> usersMap;//= new Map<Integer, User>();
    //currency repository - map<String, Currency>, map<String, Rate>
    private Role role;


    private int userId;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", usersMap=" + usersMap +
                ", role=" + role +
                ", userId=" + userId +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(Map<Integer, User> usersMap) {
        this.usersMap = usersMap;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}