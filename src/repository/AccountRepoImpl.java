package repository;

import model.*;
import model.Currency;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepoImpl implements AccountRepository {

    private final Map<Integer, List<Account>> accounts; //user id
    private final Map<Integer, List<Transaction>> transactions; // account id
    private final AtomicInteger accountCount = new AtomicInteger(1);

    public AccountRepoImpl() {
        this.accounts = new LinkedHashMap<>();
        this.transactions = new LinkedHashMap<>();
        Currency currency = new Currency("USD", "Доллар США");
        addAccount(currency);
    }

    private void addAccount(Currency currency) {
// get list  user
        int userId = 12838;

        if (!accounts.containsKey(userId)) {
            accounts.put(userId, new ArrayList<>());
        }

        accounts.get(userId).add(new Account(accountCount.getAndIncrement(), 200, currency));
        accounts.get(userId).add(new Account(accountCount.getAndIncrement(), 12.5, currency));
        accounts.get(userId).add(new Account(accountCount.getAndIncrement(), 30, currency));

    }


    @Override
    public Account addAccount(Currency currency, int userId) {
        Account account = new Account(accountCount.getAndIncrement(), 0, currency);
        accounts.put(userId, List.of(account));
        return account;
    }

    @Override
    public boolean deleteAccount(Account account) {
        accounts.remove(account.getAccountId());
        return true;

    }

    @Override
    public double getBalance(int userId, String currencyCode) {
        if (!accounts.containsKey(userId)) {
            return 0;
        }
        List<Account> userAccounts = accounts.get(userId);
        double balance = 0;
        for (Account account : userAccounts) {
            if (account.getCurrency().getCurrencyCode().equals(currencyCode)) {
                balance += account.getBalance();
            }
        }
        return balance;
    }

    @Override
    public Account getAccountById(int accountId) {

        for (Map.Entry<Integer, List<Account>> entry : accounts.entrySet()) {
            for (Account account : entry.getValue()) {
                if (account.getAccountId() == accountId) {
                    return account;
                }
            }
        }
        return null;
    }
}