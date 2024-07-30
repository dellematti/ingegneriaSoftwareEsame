package it.unimi.di.sweng.lab12.presenter;

import org.jetbrains.annotations.NotNull;

public interface InputPresenter {
  // nel caso della CommandView viene invocato con alimento e quantità
  // nel caso delle DisplayView viene invocato con il testo dell'alimento acquistato e il numero di riga
  // non è detto che servano per tutte sempre entrambi i parametri
  // a seconda delle vostre scelte progettuali potrebbe servire solo uno dei due
  void action(@NotNull String text, @NotNull String text1);
}
