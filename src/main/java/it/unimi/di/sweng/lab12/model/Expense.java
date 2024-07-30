package it.unimi.di.sweng.lab12.model;

import org.jetbrains.annotations.NotNull;

public record Expense(@NotNull FriendName friendName, int spesa) {
    public Expense {
        if(spesa < 0) throw new IllegalArgumentException("negative amount");
    }
}
