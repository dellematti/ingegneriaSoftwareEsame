package it.unimi.di.sweng.lab12;

import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
    void notifyObservers();
    void addObserver(@NotNull Observer<T> observer);
    @NotNull T getState();
}
