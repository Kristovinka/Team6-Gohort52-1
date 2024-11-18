package repository;

import model.Account;
import model.Transaction;
import model.TransactionType;

import java.util.List;
import java.util.Map;

public interface TransactionRepository {

    void withdrawMoney(int userId, Account account, double amount);

    void putMoney(int userId, Account account, double amount); // положить деньги

    void exchangeMoney(int userId, Account account, double amount); // поменять деньги

    void checkExchange_Rate(); // check курс

    Map<Integer,Transaction> getTransactionsHistory();

}
