package repository;

import model.*;
import model.Currency;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepoImpl implements AccountRepository{

    private final Map<Integer, List<Account>> accounts; //Integer - user id
    private final Map<Integer, List<Transaction>> transactions; //id - account id
    private AtomicInteger accountCount = new AtomicInteger(1);
    private AtomicInteger usersCount = new AtomicInteger(1);

    public AccountRepoImpl() {
        this.accounts = new LinkedHashMap<Integer,  List<Account>>();
        this.transactions = new LinkedHashMap<Integer, List<Transaction>>();
        Currency currency = new Currency("USD", "Доллар США");
        addAccount(currency);
    }

    private void addAccount(Currency currency, int userId) {
// get list  user
        accounts.put(userId,new Account((accountCount.getAndIncrement(),200, currency, usersCount.getAndIncrement()));
        accounts.put(userId,new Account((accountCount.getAndIncrement(),12.5, currency, usersCount.getAndIncrement()));
        accounts.put(userId,new Account(accountCount.getAndIncrement(), 30, currency, usersCount.getAndIncrement()));
    }


    @Override
    public Account addAccount(Currency currency, int userId) {
        Account account = new Account(accountCount.getAndIncrement(),0,currency, usersCount.getAndIncrement());
        accounts.put(usersCount.getAndIncrement(),account);
        return account;
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public double getBalance(Currency currency) {
        return 0;
    }

    @Override
    public void checkExchange_Rate() {

    }
}
