package repository;

import model.Account;
import model.Currency;
import model.Transaction;

public interface AccountRepository {

    Account addAccount(Currency currency, int userId);

    void deleteAccount();

    double getBalance(Currency currency);

    void checkExchange_Rate(); // check курс

}
