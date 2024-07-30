package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.InputExpenseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InputExpensePresenterTest {

    @ParameterizedTest
    @CsvSource({
            "ADA,pippo,not an integer amount",
            "ADA,-3,negative amount",
            "MARIO,3,not a friend name",
            "'',3,empty friend name"
    })
    void checkErroriInputExpense(String nome, String spesa, String errore) {
        InputExpenseView view = mock(InputExpenseView.class);
        InputExpensePresenter SUT = new InputExpensePresenter(view, mock(Model.class));

        SUT.action(nome, spesa);

        verify(view).showError(errore);
    }

    @Test
    void checkExpenseOK() {
        InputExpenseView view = mock(InputExpenseView.class);
        InputExpensePresenter SUT = new InputExpensePresenter(view, mock(Model.class));

        SUT.action("ADA", "3");

        verify(view).showSuccess();
    }
}