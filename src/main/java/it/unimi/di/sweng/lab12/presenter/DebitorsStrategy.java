package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Balance;

import java.util.Comparator;

public enum DebitorsStrategy implements CreditorsDebitorsStrategy {
    INSTANCE;

    @Override
    public boolean mustAppear(int balance) {
        return balance < 0;
    }

    @Override
    public Comparator<Balance> sortOrdering() {
        return Comparator.comparingInt(Balance::balance);
    }
}
