package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private double exchange_Rate;
    private LocalDateTime time;

    private static int counter = 1;
    private int transactionId;
    private User user;
    private double amount; //сколько деняк обмениваем
    TransactionType transactionType;
    private Currency currency;
    List<Transaction> transactions;
    //id, user, type of transaction, amount, currency
    //List<Transaction>


    public Transaction(Currency currency, double exchange_Rate, double amount, TransactionType transactionType) {
        this.exchange_Rate = exchange_Rate;
        this.transactionId = counter++;
        this.amount = amount;
        this.transactions=new ArrayList<>();
        this.time=LocalDateTime.now();
        this.currency=currency;
    }

    public double getExchange_Rate() {
        return exchange_Rate;
    }

    public void setExchange_Rate(double exchange_Rate) {
        this.exchange_Rate = exchange_Rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
