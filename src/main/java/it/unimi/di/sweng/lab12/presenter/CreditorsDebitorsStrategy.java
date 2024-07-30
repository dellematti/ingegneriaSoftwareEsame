package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Balance;

import java.util.Comparator;

public interface CreditorsDebitorsStrategy {
    boolean mustAppear(int balance);
    Comparator<Balance> sortOrdering();
}
