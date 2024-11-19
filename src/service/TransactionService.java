package service;

import model.Account;
import model.Role;
import model.Transaction;

import java.util.Currency;
import java.util.List;


public interface TransactionService {

                // Метод для создания транзакци
    void addTransaction(Transaction transaction);

    void withdrawMoney(Account account, double amount);

    void putMoney(Account account, double amount); // положить деньги

    void exchangeMoney(Account account, double amount, Currency origin, Currency result); // поменять деньги

                // Метод для получения всех транзакции
    List<Transaction> getAllTransactions();

                // Метод для получения транзакции по ID
    Transaction getTransactionById(int id);

                // Метод для удаления транзакции по ID
    void deleteTransactionById(int id);
}