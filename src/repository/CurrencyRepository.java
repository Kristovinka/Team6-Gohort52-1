package repository;

import model.Currency;

import java.util.List;

public interface CurrencyRepository {

    Currency addCurrency(String currencyCode, String title);

    List<Currency> getAllCurrencies();

    boolean deleteCurrency(Currency currency);
}
