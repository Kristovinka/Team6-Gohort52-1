package model;

//repository - map<Integer, List<Account>>, Integer - user id
//repository - map <Integer, List<Transaction>>, id - account id


import java.util.Objects;

public class Account {

    private final int userId;
    private final int accountId;
    private double balance;
    private Currency currency;

    public Account(int accountId, double balance, Currency currency, int userId) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userId == account.userId && accountId == account.accountId && Double.compare(balance, account.balance) == 0 && Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, balance, currency);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }



    public int getUserId() {
        return userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
