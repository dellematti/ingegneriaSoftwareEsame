package it.unimi.di.sweng.lab12.model;

import org.jetbrains.annotations.NotNull;

public record Balance(@NotNull FriendName friendName, int balance) {

    @NotNull
    public Balance updateBalance(@NotNull Expense expense) {
        int finalBalance = balance - expense.spesa()*20;
        return new Balance(friendName, friendName == expense.friendName() ? finalBalance + expense.spesa()*100 : finalBalance);
    }

    @NotNull
    public String format() {
        if (balance < 0) return "-" + String.format("%d,%02d", Math.abs(balance/100), Math.abs(balance%100));
        return String.format("%d,%02d", balance/100, balance%100);
    }

    @NotNull
    public String formatWithName() {
        return friendName.name() + " " + format();
    }
}
