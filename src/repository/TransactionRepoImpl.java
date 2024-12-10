package repository;

import model.Account;
import model.Currency;
import model.Transaction;

import java.util.*;

public class TransactionRepoImpl implements TransactionRepository{

    private final Map<Integer, List<Transaction>> transactions; // account id
    private final Map<String, Double> exchange_Rates; // CURRENCY id,

    public TransactionRepoImpl() {
        this.exchange_Rates = new LinkedHashMap<>();
        this.transactions = new LinkedHashMap<>();
        addExchange_Rates();
    }

    private void addExchange_Rates() {
        exchange_Rates.put("USD",1.05);
        exchange_Rates.put("EUR",1.0);
        exchange_Rates.put("UAH",4.34);
        exchange_Rates.put("PLN",0.87);
        updateTransactionsWithExchangeRates();
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
    public Transaction addTransaction(Currency currency, double amount, int accountId) { //get by user id, если список счетов пусто - добавляем
       Transaction transaction=new Transaction(currency, exchange_Rates.get(currency.getCurrencyCode()), amount);
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

    @Override
    public Map<String, Double> getExchange_Rates() {
        return exchange_Rates;
    }

    @Override
    public void updateExchangeRate(String currencyCode, double newRate) {
        if (exchange_Rates != null) {  // Проверяем, что exchange_Rates не null
            exchange_Rates.put(currencyCode, newRate);
            updateTransactionsWithExchangeRates();
        } else {
            System.err.println("Ошибка: exchange_Rates равно null.");
        }
    }

    public void updateTransactionsWithExchangeRates() {
        if (exchange_Rates == null || exchange_Rates.isEmpty()) {
            System.err.println("Ошибка: exchange_Rates пуст или null.");
            return;
        }

        for (Map.Entry<Integer, List<Transaction>> entry : transactions.entrySet()) {
            int accountId = entry.getKey();
            List<Transaction> transactionsForAccount = entry.getValue();

            for (Transaction transaction : transactionsForAccount) {
                String currencyCode = transaction.getCurrency().getCurrencyCode();

                if (exchange_Rates.containsKey(currencyCode)) {
                    double updatedExchangeRate = exchange_Rates.get(currencyCode);
                    transaction.setExchange_Rate(updatedExchangeRate);
                }
            }
        }
    }

    @Override
    public void removeExchangeRate(String currencyCode) {
        if (exchange_Rates != null && exchange_Rates.containsKey(currencyCode)) {
            exchange_Rates.remove(currencyCode);

            // Важно! Вызываем метод для обновления транзакций.
           // updateTransactionsWithExchangeRates();
        } else {
            System.err.println("Ошибка: Курс обмена для валюты " + currencyCode + " не найден или exchange_Rates равно null.");
        }
    }
}
