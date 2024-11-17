package model;

import java.util.Collection;
import java.util.Objects;

public class Currency {
    String currencyCode;
    String title;

    public Currency(String currencyCode, String title) {
        this.currencyCode = currencyCode;
        this.title = title;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(currencyCode, currency.currencyCode) && Objects.equals(title, currency.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, title);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode='" + currencyCode + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
