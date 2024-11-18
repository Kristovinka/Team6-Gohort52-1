package repository;

import model.Account;
import model.Currency;
import model.Transaction;

public interface AccountRepository {

    Account addAccount(Currency currency, int userId);

    boolean deleteAccount(Account account);

    double getBalance(int userId, String currencyCode);

}
