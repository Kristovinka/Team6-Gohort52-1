package repository;

import model.Account;
import model.Currency;
import model.Transaction;
import model.TransactionType;

import java.util.List;
import java.util.Map;

public interface TransactionRepository {


    public List<Transaction> getTransactionsByAccount(int accountId);

    public List<Transaction> getTransactionsById(int transactionId);

    public Transaction addTransaction(Currency currency, double exchange_Rate, double amount, int accountId);

    Map<Integer,List<Transaction>> getTransactionsHistory();

}
