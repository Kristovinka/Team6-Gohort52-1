package repository;

import model.Account;
import model.Currency;

public interface AccountRepository {

    Account addAccount(Currency currency, String email, String password);

    void deleteAccount();

    double getBalance(Currency currency);
}
