package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Balance;
import it.unimi.di.sweng.lab12.model.FriendName;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.DisplayView;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonBalancePresenterTest {
    @ParameterizedTest
    @CsvSource({
            "-205,'-2,05'",
            "-22,'-0,22'",
            "-2,'-0,02'",
            "5,'0,05'",
            "50,'0,50'",
            "500,'5,00'",
            "5000,'50,00'",
            "0,'0,00'"
    })
    void checkUpdateRealBalance(int balance, String formattedBalance) {
        DisplayView view = mock(DisplayView.class);
        Model model = mock(Model.class);
        when(model.getState()).thenReturn(
                new ArrayList<>(
                        List.of(
                                new Balance(FriendName.ADA, balance),
                                new Balance(FriendName.BRUNO, 0),
                                new Balance(FriendName.CHIARA, 0),
                                new Balance(FriendName.DARIO, 0),
                                new Balance(FriendName.ELENA, 0)
                        )));
        PersonBalancePresenter SUT = new PersonBalancePresenter(view, model, FriendName.ADA);

        SUT.update(model);

        verify(view).set(0, formattedBalance);
    }
}