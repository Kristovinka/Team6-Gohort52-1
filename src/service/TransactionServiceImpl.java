package service;

import model.Account;
import model.Transaction;
import repository.TransactionRepository;

import java.util.Currency;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;



    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addTransaction(Transaction transaction) {
                    // Сохранение транзакции
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public void withdrawMoney(Account account, double amount) {
        if(account.getBalance()<amount) {
            System.out.println("The balance is lower than withdrawal amount!");
        } else {
            account.setBalance(account.getBalance()-amount);
            System.out.println("Please take your money.");
        }
    }

    @Override
    public void putMoney(Account account, double amount) {
        account.setBalance(account.getBalance()+amount);
        System.out.println("Operation successful. Current balance is " + account.getBalance());
    }

    @Override
    public void exchangeMoney(Account account, double amount, Currency origin, Currency result) {
        //map<code, double>
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