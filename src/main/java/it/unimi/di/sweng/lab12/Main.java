package it.unimi.di.sweng.lab12;

import it.unimi.di.sweng.lab12.model.FriendName;
import it.unimi.di.sweng.lab12.model.Model;
import it.unimi.di.sweng.lab12.presenter.*;
import it.unimi.di.sweng.lab12.view.DisplayView;
import it.unimi.di.sweng.lab12.view.InputExpenseView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    public static final List<String> FRIENDS = List.of("ADA", "BRUNO", "CHIARA", "DARIO", "ELENA");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Common Cash");

        DisplayView ada = new DisplayView(1, "ADA");
        DisplayView bruno = new DisplayView(1, "BRUNO");
        DisplayView creditors = new DisplayView(FRIENDS.size(), "CREDITORS");
        DisplayView debtors = new DisplayView(FRIENDS.size(), "DEBTORS");

        InputExpenseView input = new InputExpenseView();

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(input, 0, 0);
        GridPane.setColumnSpan(input, GridPane.REMAINING);

        gridPane.add(ada, 0, 1);
        gridPane.add(bruno, 1, 1);
        gridPane.add(creditors, 0, 2);
        gridPane.add(debtors, 1, 2);

        Model model = new Model();

        new InputExpensePresenter(input, model);
        new PersonBalancePresenter(ada, model, FriendName.ADA);
        new PersonBalancePresenter(bruno, model, FriendName.BRUNO);
        new MultiplePersonBalancePresenter(creditors, model, CreditorsStrategy.INSTANCE);
        new MultiplePersonBalancePresenter(debtors, model, DebitorsStrategy.INSTANCE);

        model.notifyObservers();

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
