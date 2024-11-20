package service;

import model.Currency;
import model.Role;
import model.User;
import repository.CurrencyRepository;
import utils.RoleValidateException;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService{

    CurrencyRepository currencyRepository;
    User activeUser;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency addCurrency(String currencyCode, String title) throws RoleValidateException {
        Currency currency=new Currency(currencyCode, title);
        if (activeUser.getRole() != Role.ADMIN) {
            throw new RoleValidateException("You don't have admin rights!");
        } else {
            if(currencyRepository.getAllCurrencies().contains(currency)) {
                System.out.println("Currency was added already.");
            }       else { currencyRepository.addCurrency(currencyCode,title);
                return currency;
            }
        }
        return null;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.getAllCurrencies();
    }

    @Override
    public boolean deleteCurrency(Currency currency) throws RoleValidateException {
        if (activeUser.getRole() != Role.ADMIN) {
            throw new RoleValidateException("You don't have admin rights!");
        } else if (currency != null) {
            currencyRepository.deleteCurrency(currency);
            System.out.println("Currency " + currency.getTitle() + "is deleted successfully.");
            return true;
        } else {
            System.out.println("Currency doesn't exist!");
        }
        return false;

    }
    }