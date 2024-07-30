package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.Main;
import it.unimi.di.sweng.lab12.Observable;
import it.unimi.di.sweng.lab12.Observer;
import it.unimi.di.sweng.lab12.model.Balance;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.DisplayView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiplePersonBalancePresenter implements Observer<List<Balance>> {

    private @NotNull final DisplayView view;
    private @NotNull final CreditorsDebitorsStrategy strategy;

    public MultiplePersonBalancePresenter(@NotNull DisplayView view, @NotNull Model model, @NotNull CreditorsDebitorsStrategy strategy) {
        this.view = view;
        this.strategy = strategy;
        model.addObserver(this);
    }

    @Override
    public void update(@NotNull Observable<List<Balance>> observable) {
        for (int i = 0; i < Main.FRIENDS.size(); i++) view.set(i, "");
        List<Balance> balances = observable.getState();
        balances.sort(strategy.sortOrdering());
        int i = 0;
        for (Balance balance : balances) if (strategy.mustAppear(balance.balance())) view.set(i++, balance.formatWithName());
    }
}
