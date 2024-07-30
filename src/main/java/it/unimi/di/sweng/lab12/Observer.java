package it.unimi.di.sweng.lab12;

import org.jetbrains.annotations.NotNull;

public interface Observer<T> {
    void update(@NotNull Observable<T> observable);
}
