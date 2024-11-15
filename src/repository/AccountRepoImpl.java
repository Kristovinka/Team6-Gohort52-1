package repository;

import model.Account;
import model.Currency;

public class AccountRepoImpl implements AccountRepository{
    @Override
    public Account addAccount(Currency currency, String email, String password) {
        return null;
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public double getBalance(Currency currency) {
        return 0;
    }
}
