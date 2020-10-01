import View.CharacterMenu;
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
    private final Window window;
    private final MainMenu mainMenu;
    private final CharacterMenu characterMenu;
    private final GameOverMenu gameOverMenu;
    private final Stage stage;
    private String name;

    public ViewController(Window window, MainMenu mainMenu, CharacterMenu characterMenu, GameOverMenu gameOverMenu, Stage stage) {
        this.window = window;
        this.mainMenu = mainMenu;
        this.characterMenu = characterMenu;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;
        this.name = "lighter.gif";

        SimpleIntegerProperty hp = window.player.getHp();

        mainMenuButtonHandler();
        characterMenuButtonHandler();
        gameOverButtonHandler();

        // Listens to changes in hp and stops animationtimer when hp reaches 0, and switches to the game over menu
        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if ((int) newValue < 1) {

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
    public void mainMenuButtonHandler() {
        // Redirects player to character menu
        mainMenu.getPlayBtn().setOnMouseClicked(event -> stage.getScene().setRoot(characterMenu.getRoot()));

        // TODO: 2020-09-27 go to highscore menu
        mainMenu.getHighscoreBtn().setOnMouseClicked(event -> {
            // stage.getScene().setRoot(gameOverMenu.getRoot());
        });

        // When clicking on "QUIT"
        mainMenu.getQuitBtn().setOnMouseClicked(event -> closeProgram());

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
    }

    private void characterMenuButtonHandler() {
        characterMenu.getSpaceshipLighterBtn().setOnMouseClicked(event -> {
            // TODO: 2020-09-30 All spaceships should be lighter.gif
            name = "lighter.gif";
        });

        characterMenu.getSpaceshipTurtleBtn().setOnMouseClicked(event -> {
            // TODO: 2020-09-30 All spaceships should be turtle.png
            name = "turtle.png";
        });

        characterMenu.getSpaceshipThorBtn().setOnMouseClicked(event -> {
            // TODO: 2020-09-30 All spaceships should be thor.gif
            name = "thor.gif";
        });

        characterMenu.getSpaceshipUfoBtn().setOnMouseClicked(event -> {
            // TODO: 2020-09-30 All spaceships should be ufo.gif
            name = "ufo.gif";
        });

        characterMenu.getStartBtn().setOnMouseClicked(event -> {
            stage.getScene().setRoot(window.getRoot());
            window.init(name);
        });

        characterMenu.getReturnBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    // Handles button clicks in the game over menu
    private void gameOverButtonHandler() {
        gameOverMenu.getTryAgainBtn().setOnMouseClicked(event -> {
            window.init(name);
            stage.getScene().setRoot(window.getRoot());
        });

        gameOverMenu.getMainMenuBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    // Opens a dialog box when pressing quit
    private void closeProgram() {
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
