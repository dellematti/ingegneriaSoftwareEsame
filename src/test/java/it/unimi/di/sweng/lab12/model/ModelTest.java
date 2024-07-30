package it.unimi.di.sweng.lab12.model;

import it.unimi.di.sweng.lab12.Observer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelTest {
    @Test
    void checkAddExpense() {
        Model SUT = new Model();
        Balance balance1 = new Balance(FriendName.ADA, 160);
        Balance balance2 = new Balance(FriendName.BRUNO, -40);
        Balance balance3 = new Balance(FriendName.CHIARA, -40);
        Balance balance4 = new Balance(FriendName.DARIO, -40);
        Balance balance5 = new Balance(FriendName.ELENA, -40);

        SUT.addExpense(new Expense(FriendName.ADA, 2));

        assertThat(SUT.getBalances()).containsExactlyInAnyOrder(balance1, balance2, balance3, balance4, balance5);
    }

    @Test
    void checkAddConsecutiveExpense() {
        Model SUT = new Model();
        Balance balance1 = new Balance(FriendName.ADA, 120);
        Balance balance2 = new Balance(FriendName.BRUNO, 120);
        Balance balance3 = new Balance(FriendName.CHIARA, -80);
        Balance balance4 = new Balance(FriendName.DARIO, -80);
        Balance balance5 = new Balance(FriendName.ELENA, -80);

        SUT.addExpense(new Expense(FriendName.ADA, 2));
        SUT.addExpense(new Expense(FriendName.BRUNO, 2));

        assertThat(SUT.getBalances()).containsExactlyInAnyOrder(balance1, balance2, balance3, balance4, balance5);
    }

    @Test
    void notifyObserversOK() {
        Model SUT = new Model();
        @SuppressWarnings("unchecked")
        Observer<List<Balance>> observer1 = mock(Observer.class);
        @SuppressWarnings("unchecked")
        Observer<List<Balance>> observer2 = mock(Observer.class);
        SUT.addObserver(observer1);
        SUT.addObserver(observer2);

        SUT.notifyObservers();

        verify(observer1).update(SUT);
        verify(observer2).update(SUT);
    }

    @Test
    void notifyObserversAfterStateChange() {
        Model SUT = new Model();
        @SuppressWarnings("unchecked")
        Observer<List<Balance>> observer1 = mock(Observer.class);
        @SuppressWarnings("unchecked")
        Observer<List<Balance>> observer2 = mock(Observer.class);
        SUT.addObserver(observer1);
        SUT.addObserver(observer2);

        SUT.addExpense(new Expense(FriendName.ELENA, 2));
        SUT.addExpense(new Expense(FriendName.ELENA, 0));

        verify(observer1, times(1)).update(SUT);
        verify(observer2, times(1)).update(SUT);
    }
}