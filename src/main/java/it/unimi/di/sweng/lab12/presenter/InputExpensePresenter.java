package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Expense;
import it.unimi.di.sweng.lab12.model.FriendName;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.InputExpenseView;
import org.jetbrains.annotations.NotNull;

public class InputExpensePresenter implements InputPresenter {
    private @NotNull final InputExpenseView view;
    private @NotNull final Model model;

    public InputExpensePresenter(@NotNull InputExpenseView view, @NotNull Model model) {
        this.view = view;
        this.model = model;
        view.addHandlers(this);
    }

    @Override
    public void action(@NotNull String nome, @NotNull String spesa) {
        try {
            if (nome.isBlank()) throw new IllegalArgumentException("empty friend name");
            FriendName friendName = FriendName.valueOf(nome);
            int spesaNum = Integer.parseInt(spesa);
            Expense expense = new Expense(friendName, spesaNum);
            model.addExpense(expense);
            view.showSuccess();
        } catch (NumberFormatException e) {
            view.showError("not an integer amount");
        } catch (IllegalArgumentException e){
            if (e.getMessage().startsWith("No enum constant")) view.showError("not a friend name");
            else view.showError(e.getMessage());
        }
    }
}
