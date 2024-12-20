package service;

import model.Account;
import model.Role;
import model.Transaction;
import model.User;
import repository.AccountRepository;
import repository.CurrencyRepository;
import repository.TransactionRepository;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyRepository currencyRepository;
    private final AccountRepository accountRepository;
    private User user;


    public TransactionServiceImpl(TransactionRepository transactionRepository, CurrencyRepository currencyRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.currencyRepository = currencyRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addTransaction(String codeCurrency, double amount, int accountId) {
                    // Сохранение транзакции
        System.out.println( "new transaction: " + transactionRepository.
                addTransaction(currencyRepository.
                        getCurrencyByCode(codeCurrency),amount,accountId));
    }

    @Override
    public void withdrawMoney(int accountId, double amount) {
        Account account = accountRepository.getAccountById(accountId);
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
    public void putMoney(int accountId, double amount) {
        Account account = accountRepository.getAccountById(accountId);
        if(account==null) {
            System.out.println("Account doesn't exist!");
        } else if (user==null || user.getRole() ==Role.BLOCKED) {
            System.out.println("User is blocked or doesn't exist.");
        } else
        account.setBalance(account.getBalance()+amount);
        System.out.println("Operation successful. Current balance is " + account.getBalance());
    }

    @Override
    public void exchangeMoney(int accountId, int toAccountId, double amount) {
        Account account = accountRepository.getAccountById(accountId);

        //map<code, double>
    }

    @Override
    public Map<Integer, List<Transaction>> getAllTransactions() {
        // Находим все транзакции
        return transactionRepository.getTransactionsHistory();
    }

    @Override
    public List<Transaction> getTransactionsById(int id) {
        // Получение транзакций по ID
        return transactionRepository.getTransactionsById(id);
    }

    @Override
    public Map<String, Double> getExchange_Rates() {
        return transactionRepository.getExchange_Rates();
    }

    @Override
    public void updateExchangeRate(String currencyCode, double newRate) {
        transactionRepository.updateExchangeRate(currencyCode,newRate);
    }

    @Override
    public void addExchangeRate(String currencyCode,String currency, double newRate) {
        currencyRepository.addCurrency(currencyCode, currency);
        transactionRepository.updateExchangeRate(currencyCode, newRate);
    }

    @Override
    public void removeExchangeRate(String currencyCode) {
        transactionRepository.removeExchangeRate(currencyCode);
        currencyRepository.deleteCurrency(currencyRepository.getCurrencyByCode(currencyCode));
    }

    //public List<Transaction> getTransactionsbyExchange_Rate(int id) {}

}