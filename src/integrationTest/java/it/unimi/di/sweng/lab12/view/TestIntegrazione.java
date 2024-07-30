package it.unimi.di.sweng.lab12.view;


import it.unimi.di.sweng.lab12.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.util.introspection.FieldSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;


@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {


    private InputExpenseView input;
    private DisplayView displayAda;
    private DisplayView displayBruno;
    private DisplayView displayCreditors;
    private DisplayView displayDebtors;
    private Label errorMessage;


    private static final boolean HEADLESS = false;

    @BeforeAll
    public static void setupSpec() {
        if (HEADLESS) System.setProperty("testfx.headless", "true");
    }

    @Start
    public void start(Stage primaryStage) {

        Main m = new Main();
        m.start(primaryStage);

        GridPane gp = (GridPane) primaryStage.getScene().getRoot();
        ObservableList<Node> view = gp.getChildren();

        input = (InputExpenseView) view.get(0);
        displayAda = (DisplayView) view.get(1);
        displayBruno = (DisplayView) view.get(2);
        displayCreditors = (DisplayView) view.get(3);
        displayDebtors = (DisplayView) view.get(4);
        errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, input);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            '',6,empty friend name
            CARLO,6,not a friend name
            ADA,-6,negative amount
            ADA,mille,not an integer amount
            ADA,6.5,not an integer amount        
            """)
    void testInputError(String name, String amount, String error, FxRobot robot) {
        robot.doubleClickOn(input.text);
        robot.write(name);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write(amount);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);
        verifyThat(errorMessage, hasText(error));
    }

    @Test
    public void testCreditorsDisplayStart(FxRobot robot) {
        assertThat(displayCreditors.get(0)).isEqualTo("");
        assertThat(displayDebtors.get(0)).isEqualTo("");
    }

    @Test
    public void testSingleDisplayStart(FxRobot robot) {
        assertThat(displayAda.get(0)).isEqualTo("0,00");
        assertThat(displayBruno.get(0)).isEqualTo("0,00");
    }

    @Test
    void testCorrectSingleInput(FxRobot robot) {
        robot.doubleClickOn(input.text);
        robot.write("ADA");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("6");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        verifyThat(errorMessage, hasText(""));
        assertThat(displayAda.get(0)).isEqualTo("4,80");
        assertThat(displayCreditors.get(0)).startsWith("ADA").endsWith("4,80");
        assertThat(displayBruno.get(0)).isEqualTo("-1,20");
        assertThat(displayDebtors.get(0)).endsWith("-1,20");
    }

    @Test
    void testCorrectMultInput(FxRobot robot) {
        robot.doubleClickOn(input.text);
        robot.write("CHIARA");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("4");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        robot.doubleClickOn(input.text);
        robot.write("ADA");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("1");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        verifyThat(errorMessage, hasText(""));
        assertThat(displayAda.get(0)).isEqualTo("0,00");
        assertThat(displayCreditors.get(0)).startsWith("CHIARA").endsWith("3,00");
        assertThat(displayCreditors.get(1)).isEqualTo("");
        assertThat(displayBruno.get(0)).isEqualTo("-1,00");
        assertThat(displayDebtors.get(0)).endsWith("-1,00");
        assertThat(displayDebtors.get(3)).isEqualTo("");

    }

    @Test
    void testCorrectOrderDisplay(FxRobot robot) {
        robot.doubleClickOn(input.text);
        robot.write("ELENA");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("5");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        robot.doubleClickOn(input.text);
        robot.write("BRUNO");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("1");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        robot.doubleClickOn(input.text);
        robot.write("DARIO");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.doubleClickOn(input.num);
        robot.write("10");
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);
        robot.clickOn(input.addButton);

        verifyThat(errorMessage, hasText(""));
        assertThat(displayAda.get(0)).isEqualTo("-3,20");
        assertThat(displayCreditors.get(0)).startsWith("DARIO").endsWith("6,80");
        assertThat(displayCreditors.get(1)).startsWith("ELENA").endsWith("1,80");
        assertThat(displayDebtors.get(0)).endsWith("-3,20");
        assertThat(displayDebtors.get(2)).startsWith("BRUNO").endsWith("-2,20");

    }
}
