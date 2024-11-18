package repository;

import model.Account;
import model.Transaction;

import java.util.Currency;
import java.util.Map;

public class TransactionRepoImpl implements TransactionRepository{

    Transaction transaction;

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

    }


    @Override
    public Map<Integer,Transaction> getTransactionsHistory() {
        return transaction.getTransactions();
    }
}
