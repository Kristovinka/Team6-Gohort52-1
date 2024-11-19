package servise;

import model.Transaction;
import repository.TransactionRepository;
import service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;



    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(Transaction transaction) {
                    // Сохранение транзакции
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
                    // Находим  все транзакции
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(int id) {
                    // Получение транзакции по ID
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Транзакция с таким ID не найдена: " + id));
    }

    @Override
    public void deleteTransactionById(int id) {
                    // Удаление транзакции по ID
        transactionRepository.deleteById(id);
    }
}