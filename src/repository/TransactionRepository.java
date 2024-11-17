package repository;

import model.Transaction;

import java.util.List;

public interface TransactionRepository {

    void addTransaction();

    void withdrawMoney(); // снять деньги

    void putMoney(); // положить деньги

    void exchangeMoney(); // поменять деньги

    List<Transaction> getTransactionsHistory();


}
