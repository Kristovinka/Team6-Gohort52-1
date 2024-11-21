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

    public Transaction addTransaction(Currency currency, double amount, int accountId);

    Map<Integer,List<Transaction>> getTransactionsHistory();

    public Map<String, Double> getExchange_Rates();

    public void updateExchangeRate(String currencyCode, double newRate);

    public void removeExchangeRate(String currencyCode);

}
