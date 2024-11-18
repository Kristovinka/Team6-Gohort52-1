package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String email;
    private String password;
    //Map<Integer, User> usersMap;//= new Map<Integer, User>();
    List<Transaction> userTransactions;
    //currency repository - map<String, Currency>, map<String, Rate>
    private Role role;

    private int userId=0;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userId = userId++;
        userTransactions=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
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

    public List<Transaction> getUserTransactions() {
        return userTransactions;
    }

}