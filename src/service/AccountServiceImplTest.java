package service;

import model.Account;
import model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class AccountServiceImplTest {

    private AccountServiceImpl accountService;
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository = new InMemoryAccountRepository();
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void testOpenAccount() {
        int userId = 1;
        String currencyCode = "USD";

        accountService.openAccount(userId, currencyCode);

        List<Account> accounts = accountRepository.getAccountsByUser_id(userId);
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals(currencyCode, accounts.get(0).getCurrency().getCurrencyCode());
    }

    @Test
    public void testDeposit() throws CustomException {
        int userId = 1;
        Account account = accountRepository.addAccount(new Currency("USD", "Dollar"), userId);
        String accountId = String.valueOf(account.getAccountId());
        double amount = 100.0;

        accountService.deposit(accountId, amount);

        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testWithdraw() throws CustomException {
        int userId = 1;
        Account account = accountRepository.addAccount(new Currency("USD", "Dollar"), userId);
        String accountId = String.valueOf(account.getAccountId());
        double amount = 50.0;
        account.setBalance(100.0);

        accountService.withdraw(accountId, amount);

        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void testCloseAccount() throws CustomException {
        int userId = 1;
        Account account = accountRepository.addAccount(new Currency("USD", "Dollar"), userId);
        String accountId = String.valueOf(account.getAccountId());

        accountService.closeAccount(accountId);

        List<Account> accounts = accountRepository.getAccountsByUser_id(userId);
        assertTrue(accounts.isEmpty());
    }

    // Реализация InMemoryAccountRepository для тестирования
    private class InMemoryAccountRepository implements AccountRepository {
        private List<Account> accounts = new ArrayList<>();
        private int currentId = 1;

        @Override
        public Account addAccount(Currency currency, int userId) {
            Account account = new Account(currentId++, 0, currency);
            accounts.add(account);
            return account;
        }

        @Override
        public boolean deleteAccount(Account account) {
            return accounts.removeIf(a -> a.getAccountId() == account.getAccountId());
        }

        @Override
        public Double getBalance(int userId, String currencyCode) {
            return accounts.stream()
                    .filter(a -> a.getUserId() == userId && a.getCurrency().getCurrencyCode().equals(currencyCode))
                    .mapToDouble(Account::getBalance)
                    .sum();
        }

        @Override
        public Account getAccountById(int accountId) {
            return accounts.stream().filter(a -> a.getAccountId() == accountId).findFirst().orElse(null);
        }

        @Override
        public List<Account> getAccountsByUser_id(int userId) {
            return new ArrayList<>(accounts);
        }
    }
}
