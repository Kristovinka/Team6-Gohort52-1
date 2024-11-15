package repository;

import model.Transaction;

import java.util.List;

public interface TransactionRepository {

    void addTransaction();

    List<Transaction> getTransactionsHistory();


}
