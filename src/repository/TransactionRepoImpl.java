package repository;

import model.Account;
import model.Transaction;

import java.util.Map;

public class TransactionRepoImpl implements TransactionRepository{


    @Override
    public void withdrawMoney(int userId, Account account, double amount) {

    }

    @Override
    public void putMoney(int userId, Account account, double amount) {

    }

    @Override
    public void exchangeMoney(int userId, Account account, double amount) {

    }


    @Override
    public Map<Integer,Transaction> getTransactionsHistory() {
        return null;
    }
}
