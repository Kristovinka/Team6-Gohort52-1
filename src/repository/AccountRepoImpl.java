package repository;

import model.*;
import model.Currency;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepoImpl implements AccountRepository {

    private final Map<Integer, List<Account>> accounts; //user id
    private final AtomicInteger accountCount = new AtomicInteger(1);

    public AccountRepoImpl() {
        this.accounts = new LinkedHashMap<>();
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
    public Account addAccount(Currency currency, int userId) { //get by user id, если список счетов пусто - добавляем
        Account account = new Account(accountCount.getAndIncrement(), 0, currency);

        if (!accounts.containsKey(userId)) {
            accounts.put(userId, new ArrayList<>(List.of(account)));
        } else {
            List<Account> accounts1 = accounts.get(userId);
            accounts1.add(account);
            accounts.put(userId, accounts1);
        }
        return account;
    }

    @Override
    public boolean deleteAccount(Account account) {
//        accounts.remove(account.getAccountId());
//        return true;
        for (Map.Entry<Integer, List<Account>> entry : accounts.entrySet()) {
            List<Account> userAccounts = entry.getValue();
            userAccounts.removeIf(a -> a.getAccountId() == account.getAccountId());
            if (userAccounts.isEmpty()) {
                accounts.remove(entry.getKey());
            }
            return true;
        }
        return false;

    }

    @Override
    public Double getBalance(int userId, String currencyCode) {
        if (!accounts.containsKey(userId)) {
            return null;
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

    //get list accounts

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

    @Override
    public List <Account> getAccountsByUser_id(int userId) {
        if (!accounts.containsKey(userId)) {
            return null;
        }
        return accounts.get(userId);
    }
}