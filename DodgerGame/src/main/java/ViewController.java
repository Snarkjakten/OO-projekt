import View.GameOverMenu;
import View.MainMenu;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class ViewController {
    private Window window;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private Stage stage;

    private SimpleIntegerProperty hp;

    public ViewController(Window window, MainMenu mainMenu, GameOverMenu gameOverMenu, Stage stage){
        this.window = window;
        this.mainMenu = mainMenu;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;

        hp = window.spaceship.getHp();

        mainMenuButtonHandler();
        gameOverButtonHandler();

        // Listens to changes in hp and stops animationtimer when hp reaches 0, and switches to the game over menu
        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if ((int) newValue < 1){

                    stage.getScene().setOnMouseClicked(null);
                    stage.getScene().setOnKeyPressed(null);
                    stage.getScene().setOnKeyReleased(null);

                    window.stopAnimationTimer();
                    int points = window.getPoints();
                    gameOverMenu.showScore(points);
                    stage.getScene().setRoot(gameOverMenu.getRoot());
                }
            }
        };

        hp.addListener(listener);
    }

    // Handles button clicks in the main menu
    public void mainMenuButtonHandler(){
        // Starts the game when clicking on "PLAY"
        mainMenu.getPlayBtn().setOnMouseClicked(event -> {
            window.init();
            stage.getScene().setRoot(window.getRoot());
        });

        // TODO: 2020-09-27 go to highscore menu
        mainMenu.getHighscoreBtn().setOnMouseClicked(event -> {
            // stage.getScene().setRoot(gameOverMenu.getRoot());
        });

        // When clicking on "QUIT"
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
            stage.getScene().setRoot(window.getRoot());
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
}
