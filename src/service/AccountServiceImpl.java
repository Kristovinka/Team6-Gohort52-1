package service;

import model.Account;
import repository.AccountRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    public AccountServiceImpl(AccountRepository accountRepository) {

    }

    @Override
    public Account addAccount(String currencyCode, int userId) {
        return null;
    }

    @Override
    public boolean deleteAccount(int accountId) {
        return false;
    }

    @Override
    public Double getBalance(int userId, String currencyCode) {
        return 0.0;
    }

    @Override
    public Account getAccountById(int id) {
        return null;
    }

    @Override
    public List<Account> getAccountsByUser_id(int userId) {
        return List.of();
    }
}
