package it.unimi.di.sweng.lab12.model;

import it.unimi.di.sweng.lab12.Observable;
import it.unimi.di.sweng.lab12.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements Observable<List<Balance>> {

    private @NotNull final Map<FriendName, Balance> balanceMap = new HashMap<>();
    private @NotNull final List<Observer<List<Balance>>> observers = new ArrayList<>();

    public Model() {
        for (FriendName friendName : FriendName.values()) balanceMap.put(friendName, new Balance(friendName, 0));
    }

    public void addExpense(@NotNull Expense expense) {
        if (expense.spesa() == 0) return;
        for (FriendName friendName : FriendName.values()) balanceMap.put(friendName, balanceMap.get(friendName).updateBalance(expense));
        notifyObservers();
    }

    @NotNull
    public List<Balance> getBalances() {
        return new ArrayList<>(balanceMap.values());
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Balance>> observer : observers) observer.update(this);
    }

    @Override
    public void addObserver(@NotNull Observer<List<Balance>> observer) {
        observers.add(observer);
    }

    @Override
    public @NotNull List<Balance> getState() {
        return getBalances();
    }
}
