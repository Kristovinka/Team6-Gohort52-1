package repository;

import model.Account;
import model.Currency;
import model.Transaction;

public interface AccountRepository {

    Account addAccount(Currency currency, int userId);

    boolean deleteAccount(Account account);

    Double getBalance(int userId, String currencyCode);

    public Account getAccountById(int id);

}
