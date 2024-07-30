package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.Observable;
import it.unimi.di.sweng.lab12.Observer;
import it.unimi.di.sweng.lab12.model.Balance;
import it.unimi.di.sweng.lab12.model.FriendName;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.DisplayView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PersonBalancePresenter implements Observer<List<Balance>> {

    private @NotNull final DisplayView view;
    private @NotNull final FriendName friendName;

    public PersonBalancePresenter(@NotNull DisplayView view, @NotNull Model model, @NotNull FriendName friendName) {
        this.view = view;
        this.friendName = friendName;
        model.addObserver(this);
    }

    @Override
    public void update(@NotNull Observable<List<Balance>> observable) {
        for (Balance balance : observable.getState()) {
            if (balance.friendName() == friendName) {
                view.set(0, balance.format());
                return;
            }
        }
    }
}
