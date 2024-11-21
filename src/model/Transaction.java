package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private double exchange_Rate;
    private LocalDateTime time;

    private final AtomicInteger transactionId = new AtomicInteger(100);
    private User user;
    private double amount; //сколько деняк обмениваем
    TransactionType transactionType;
    private Currency currency;
    //Map<Integer,Transaction> transactions;
    //id, user, type of transaction, amount, currency
    //List<Transaction>


    public Transaction(Currency currency, double exchange_Rate, double amount) {
        this.exchange_Rate = exchange_Rate;
        this.amount = amount;
        //this.transactions=new LinkedHashMap<Integer,Transaction>();
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

    public User getUser() {
        return user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AtomicInteger getTransactionId() {
        return transactionId;
    }
}
