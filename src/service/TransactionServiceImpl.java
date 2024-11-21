package service;

import model.Account;
import model.Role;
import model.Transaction;
import model.User;
import repository.CurrencyRepository;
import repository.TransactionRepository;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyRepository currencyRepository;
    private User user;


    public TransactionServiceImpl(TransactionRepository transactionRepository, CurrencyRepository currencyRepository) {
        this.transactionRepository = transactionRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void addTransaction(String codeCurrency, double amount, int accountId) {
                    // Сохранение транзакции
        transactionRepository.addTransaction(currencyRepository.getCurrencyByCode(codeCurrency),amount,accountId);
    }

    @Override
    public void withdrawMoney(Account account, double amount) {
        if(account==null) {
            System.out.println("Account doesn't exist!");
        } else if (user==null || user.getRole() ==Role.BLOCKED) {
            System.out.println("User is blocked or doesn't exist.");
        }        if(account.getBalance()<amount) {
            System.out.println("The balance is lower than withdrawal amount!");
        } else {
            account.setBalance(account.getBalance()-amount);
            System.out.println("Please take your money. Current balance is: " + account.getBalance());
        }
    }

    @Override
    public void putMoney(Account account, double amount) {
        if(account==null) {
            System.out.println("Account doesn't exist!");
        } else if (user==null || user.getRole() ==Role.BLOCKED) {
            System.out.println("User is blocked or doesn't exist.");
        } else
        account.setBalance(account.getBalance()+amount);
        System.out.println("Operation successful. Current balance is " + account.getBalance());
    }

    @Override
    public void exchangeMoney(Account account, double amount, Currency origin, Currency result) {
        //map<code, double>
    }

    @Override
    public Map<Integer, List<Transaction>> getAllTransactions() {
        // Находим  все транзакции
        return transactionRepository.getTransactionsHistory();
    }

    @Override
    public List<Transaction> getTransactionsById(int id) {
        // Получение транзакций по ID
        return transactionRepository.getTransactionsById(id);
    }

}