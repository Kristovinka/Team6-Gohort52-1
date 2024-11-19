package repository;

import model.Account;
import model.Currency;
import model.Transaction;
import model.TransactionType;

import java.util.*;

public class TransactionRepoImpl implements TransactionRepository{

    private final Map<Integer, List<Transaction>> transactions; // account id

    public TransactionRepoImpl(Map<Integer, List<Transaction>> transactions) {
        this.transactions = new LinkedHashMap<>();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(int accountId){
        if (!transactions.containsKey(accountId)) {
            return null;
        }
        return transactions.get(accountId);
    }

    @Override
    public List<Transaction> getTransactionsById(int transactionId){
        if (!transactions.containsKey(transactionId)) {
            return null;
        }
        return transactions.get(transactionId);
    }

    @Override
    public Transaction addTransaction(Currency currency, double exchange_Rate, double amount, int accountId) { //get by user id, если список счетов пусто - добавляем
       Transaction transaction=new Transaction(currency, exchange_Rate, amount);
        if (!transactions.containsKey(accountId)) {
            transactions.put(accountId, new ArrayList<>(List.of(transaction)));
        } else {
            List<Transaction> transactionList = transactions.get(accountId);
            transactionList.add(transaction);
            transactions.put(accountId, transactionList);
        }
        return transaction;
    }

    @Override
    public Map<Integer,List<Transaction>> getTransactionsHistory() {
        return transactions;
    }
}
