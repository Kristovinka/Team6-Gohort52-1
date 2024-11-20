package service;

import model.Account;
import model.Currency;

import java.util.List;

public interface AccountService {

    Account addAccount(String currencyCode, int userId);

    boolean deleteAccount(int accountId);

    Double getBalance(int userId, String currencyCode);

    public Account getAccountById(int id);

    public List<Account> getAccountsByUser_id(int userId);

}
