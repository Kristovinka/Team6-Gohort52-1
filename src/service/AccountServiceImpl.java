package service;

import model.Account;
import model.Currency;
import repository.AccountRepository;
import repository.CurrencyRepository;
import repository.UserRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Account addAccount(String currencyCode, int userId) {

        if(currencyRepository.getCurrencyByCode(currencyCode) == null) {
            System.out.println(currencyCode + ": не существует");
            return null;
        }
        if(userRepository.getUserById(userId) == null) {
            System.out.println("User не существует");
            return null;
        }
        return accountRepository.addAccount(currencyRepository.getCurrencyByCode(currencyCode), userId);
    }

    @Override
    public boolean deleteAccount(int accountId) {
        if(accountRepository.getAccountById(accountId) == null) {
            System.out.println("Account не существует");
            return false;}
        return accountRepository.deleteAccount(accountRepository.getAccountById(accountId));
    }

    @Override
    public Double getBalance(int userId, String currencyCode) {

        if(userRepository.getUserById(userId) == null) {
            System.out.println("User не существует");
            return null;
        }
        if(currencyRepository.getCurrencyByCode(currencyCode) == null) {
            System.out.println(currencyCode + ": не существует");
            return null;
        }
        return accountRepository.getBalance(userId, currencyCode);
    }

    @Override
    public List<Currency> getCurrenciesByUser(int userId) {

        if(userRepository.getUserById(userId) == null) {
            System.out.println("User не существует");
            return null;
        }
        return accountRepository.getCurrenciesByUser(userId);
    }

    @Override
    public Account getAccountById(int accountId) {

        if(accountRepository.getAccountById(accountId) == null) {
            System.out.println("Account не существует");
            return null;}

        return accountRepository.getAccountById(accountId);
    }

    @Override
    public List<Account> getAccountsByUser_id(int userId) {
        if(userRepository.getUserById(userId) == null) {
            System.out.println("User не существует");
            return null;
        }
        return accountRepository.getAccountsByUser_id(userId);
    }
}
