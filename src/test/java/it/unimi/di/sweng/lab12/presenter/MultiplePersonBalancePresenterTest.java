package it.unimi.di.sweng.lab12.presenter;

import it.unimi.di.sweng.lab12.model.Balance;
import it.unimi.di.sweng.lab12.model.FriendName;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.view.DisplayView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultiplePersonBalancePresenterTest {
    @Test
    void checkUpdateStart() {
        DisplayView view = mock(DisplayView.class);
        Model model = mock(Model.class);
        when(model.getState()).thenReturn(
                new ArrayList<>(
                        List.of(
                                new Balance(FriendName.ADA, 0),
                                new Balance(FriendName.BRUNO, 0),
                                new Balance(FriendName.CHIARA, 0),
                                new Balance(FriendName.DARIO, 0),
                                new Balance(FriendName.ELENA, 0)
                        )));
        MultiplePersonBalancePresenter SUT = new MultiplePersonBalancePresenter(view, model, CreditorsStrategy.INSTANCE);

        SUT.update(model);

        verify(view).set(0, "");
        verify(view).set(1, "");
        verify(view).set(2, "");
        verify(view).set(3, "");
        verify(view).set(4, "");
    }

    @Test
    void checkUpdateCreditors() {
        DisplayView view = mock(DisplayView.class);
        Model model = mock(Model.class);
        when(model.getState()).thenReturn(
                new ArrayList<>(
                        List.of(
                                new Balance(FriendName.ADA, -203),
                                new Balance(FriendName.BRUNO, 0),
                                new Balance(FriendName.CHIARA, 0),
                                new Balance(FriendName.DARIO, 32),
                                new Balance(FriendName.ELENA, 1)
                        )));
        MultiplePersonBalancePresenter SUT = new MultiplePersonBalancePresenter(view, model, CreditorsStrategy.INSTANCE);

        SUT.update(model);

        verify(view).set(0, "DARIO 0,32");
        verify(view).set(1, "ELENA 0,01");
        verify(view).set(2, "");
        verify(view).set(3, "");
        verify(view).set(4, "");
    }

    @Test
    void checkUpdateDebitors() {
        DisplayView view = mock(DisplayView.class);
        Model model = mock(Model.class);
        when(model.getState()).thenReturn(
                new ArrayList<>(
                        List.of(
                                new Balance(FriendName.ADA, -203),
                                new Balance(FriendName.BRUNO, 0),
                                new Balance(FriendName.CHIARA, -85),
                                new Balance(FriendName.DARIO, 32),
                                new Balance(FriendName.ELENA, 1)
                        )));
        MultiplePersonBalancePresenter SUT = new MultiplePersonBalancePresenter(view, model, DebitorsStrategy.INSTANCE);

        SUT.update(model);

        verify(view).set(0, "ADA -2,03");
        verify(view).set(1, "CHIARA -0,85");
        verify(view).set(2, "");
        verify(view).set(3, "");
        verify(view).set(4, "");
    }
}