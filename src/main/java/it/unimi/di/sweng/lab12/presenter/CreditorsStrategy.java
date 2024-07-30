package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Balance;

import java.util.Comparator;

public enum CreditorsStrategy implements CreditorsDebitorsStrategy {
    INSTANCE;

    @Override
    public boolean mustAppear(int balance) {
        return balance > 0;
    }

    @Override
    public Comparator<Balance> sortOrdering() {
        return (o1, o2) -> Integer.compare(o2.balance(), o1.balance());
    }
}
