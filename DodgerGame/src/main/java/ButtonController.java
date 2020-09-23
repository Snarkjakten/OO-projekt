import javafx.beans.InvalidationListener;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import javafx.beans.Observable;

/**
 * @Author Isak Almeros
 */

public class ButtonController {
    private Window window;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private Stage stage;

    SimpleIntegerProperty hp;

    public ButtonController(Window window, MainMenu mainMenu, GameOverMenu gameOverMenu, Stage stage){
        this.window = window;
        this.mainMenu = mainMenu;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;

        mainMenuButtonHandler();
        gameOverButtonHandler();

        simulateHpDamage();

        hp = window.spaceship.hp;
/*
        hp.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

            }
        });

 */

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if ((int) newValue < 1){

                    System.out.println("spökboll");
                    stage.getScene().setOnMouseClicked(null);
                    stage.getScene().setOnKeyPressed(null);
                    stage.getScene().setOnKeyReleased(null);
                    stage.getScene().setRoot(gameOverMenu.getRoot());
                }
            }
        };

        hp.addListener(listener);
    }

    // Handles button clicks in the main menu
    private void mainMenuButtonHandler(){
        mainMenu.getPlayBtn().setOnMouseClicked(event -> {
            window.init();
            stage.getScene().setRoot(window.getWin());
        });

        mainMenu.getHighscoreBtn().setOnMouseClicked(event -> {
            stage.getScene().setRoot(gameOverMenu.getRoot());
        });

        mainMenu.getQuitBtn().setOnMouseClicked(event -> {
            closeProgram();
        });

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
    }

    // Handles button clicks in the game over menu
    private void gameOverButtonHandler(){
        gameOverMenu.getTryAgainBtn().setOnMouseClicked(event -> {
            window.init();
            stage.getScene().setRoot(window.getWin());
        });

        gameOverMenu.getMainMenuBtn().setOnMouseClicked(event -> {
            stage.getScene().setRoot(mainMenu.getRoot());
        });
    }

    // Opens a dialog box when pressing quit
    private void closeProgram(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit game");
        alert.setContentText("Are you sure you want to quit?");

        // Replace buttons
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }

    // Delete method later
    private void simulateHpDamage(){
        mainMenu.getHp().setOnMouseClicked(event -> {
            SimpleIntegerProperty damage = new SimpleIntegerProperty(100);

            System.out.println(hp);

            NumberBinding sub = hp.subtract(damage);
            hp.set(sub.intValue());
            System.out.println(hp);
        });
    }
}
