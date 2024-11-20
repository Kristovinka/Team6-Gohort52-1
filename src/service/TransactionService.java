package service;

import model.Account;
import model.Role;
import model.Transaction;

import java.util.Currency;
import java.util.List;
import java.util.Map;


public interface TransactionService {

    // Метод для создания транзакци
    void addTransaction(Transaction transaction);

    void withdrawMoney(Account account, double amount);

    void putMoney(Account account, double amount); // положить деньги

    void exchangeMoney(Account account, double amount, Currency origin, Currency result); // поменять деньги

    // Метод для получения всех транзакции
    Map<Integer, List<Transaction>> getAllTransactions();

    // Метод для получения транзакции по ID
    List<Transaction> getTransactionsById(int id);

}