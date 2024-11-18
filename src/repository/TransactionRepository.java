package repository;

import model.Account;
import model.Transaction;
import model.TransactionType;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {

    void withdrawMoney(Account account, double amount);

    void putMoney(Account account, double amount); // положить деньги

    void exchangeMoney(Account account, double amount, Currency origin, Currency result); // поменять деньги

    void checkExchange_Rate(); // check курс

    Map<Integer,Transaction> getTransactionsHistory();

}
