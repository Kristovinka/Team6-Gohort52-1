package repository;

import model.Account;
import model.Transaction;

import java.util.*;

public class TransactionRepoImpl implements TransactionRepository{

    private final Map<Integer, List<Transaction>> transactions; // account id

    public TransactionRepoImpl(Map<Integer, List<Transaction>> transactions) {
        this.transactions = new LinkedHashMap<>();
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
    public void checkExchange_Rate() {
        // Заглушка: имитируем получение актуальных курсов валют
        Map<String, Double> exchangeRates = getMockExchangeRates();

        System.out.println("Current exchange rates:");
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private Map<String, Double> getMockExchangeRates() {
        // Имитируем актуальные данные о курсах валют
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD/EUR", 0.92);
        exchangeRates.put("EUR/USD", 1.09);
        exchangeRates.put("USD/GBP", 0.78);
        exchangeRates.put("GBP/USD", 1.28);
        return exchangeRates;
    }

    @Override
    public Map<Integer,List<Transaction>> getTransactionsHistory() {
        return transactions;
    }
}
