package servise;

import model.Transaction;

import java.util.List;


public interface TransactionService {

                // Метод для создания транзакци
    void createTransaction(Transaction transaction);

                // Метод для получения всех транзакции
    List<Transaction> getAllTransactions();

                // Метод для получения транзакции по ID
    Transaction getTransactionById(int id);

                // Метод для удаления транзакции по ID
    void deleteTransactionById(int id);
}