import Interfaces.IGameOverObserver;
import View.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class ViewController implements IGameOverObserver {
    private final Window window;
    private final MainMenu mainMenu;
    private final HighScoreMenu highScoreMenu;
    private final CharacterMenu characterMenu;
    private final GameOverMenu gameOverMenu;
    private final Stage stage;
    private int spaceshipChoice;
    private GameObjectGUI gameObjectGUI;
    AnimationTimer gameLoop;

    public ViewController(Window window, MainMenu mainMenu, HighScoreMenu highScoreMenu, CharacterMenu characterMenu,
                          GameOverMenu gameOverMenu, Stage stage, AnimationTimer gameLoop, GameObjectGUI gameObjectGUI) {
        this.window = window;
        this.mainMenu = mainMenu;
        this.highScoreMenu = highScoreMenu;
        this.characterMenu = characterMenu;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;
        this.spaceshipChoice = 0;
        this.gameLoop = gameLoop;
        this.gameObjectGUI = gameObjectGUI;

        mainMenuButtonHandler();
        characterMenuButtonHandler();
        gameOverButtonHandler();
        highScoreButtonHandler();
    }

    // Handles button clicks in the main menu
    public void mainMenuButtonHandler() {
        // Redirects player to character menu
        mainMenu.getPlayBtn().setOnMouseClicked(event -> stage.getScene().setRoot(characterMenu.getRoot()));

        // TODO: 2020-09-27 go to highscore menu
        mainMenu.getHighScoreBtn().setOnMouseClicked(event -> {
            stage.getScene().setRoot(highScoreMenu.getRoot());
        });

        // When clicking on "QUIT"
        mainMenu.getQuitBtn().setOnMouseClicked(event -> closeProgram());

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
    }

    private void highScoreButtonHandler(){
        highScoreMenu.getMainMenuBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    // @Author Tobias Engblom
    private void characterMenuButtonHandler() {
        characterMenu.getSpaceshipLighterBtn().setOnMouseClicked(event -> {
            spaceshipChoice = 1;
            characterMenu.getSpaceshipLighterBtn().getButtonBackground().setStrokeWidth(5);
            characterMenu.getSpaceshipTurtleBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipThorBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipUfoBtn().getButtonBackground().setStrokeWidth(1);
        });

        characterMenu.getSpaceshipTurtleBtn().setOnMouseClicked(event -> {
            spaceshipChoice = 2;
            characterMenu.getSpaceshipLighterBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipTurtleBtn().getButtonBackground().setStrokeWidth(5);
            characterMenu.getSpaceshipThorBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipUfoBtn().getButtonBackground().setStrokeWidth(1);
        });

        characterMenu.getSpaceshipThorBtn().setOnMouseClicked(event -> {
            spaceshipChoice = 3;
            characterMenu.getSpaceshipLighterBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipTurtleBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipThorBtn().getButtonBackground().setStrokeWidth(5);
            characterMenu.getSpaceshipUfoBtn().getButtonBackground().setStrokeWidth(1);
        });

        characterMenu.getSpaceshipUfoBtn().setOnMouseClicked(event -> {
            spaceshipChoice = 4;
            characterMenu.getSpaceshipLighterBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipTurtleBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipThorBtn().getButtonBackground().setStrokeWidth(1);
            characterMenu.getSpaceshipUfoBtn().getButtonBackground().setStrokeWidth(5);
        });

        characterMenu.getStartBtn().setOnMouseClicked(event -> {
            if (spaceshipChoice != 0) {
                gameObjectGUI.chooseSpaceshipImage(spaceshipChoice);
                stage.getScene().setRoot(window.getRoot());
                gameLoop.start();
                window.init();
            }
        });

        characterMenu.getReturnBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    // Handles button clicks in the game over menu
    private void gameOverButtonHandler() {
        gameOverMenu.getTryAgainBtn().setOnMouseClicked(event -> {
            gameLoop.start();
            stage.getScene().setRoot(window.getRoot());
            window.init();
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

    @Override
    public void actOnEvent(boolean isGameOver) {
        if(isGameOver) {
            //TODO: change this
            int points = 100;
            gameOverMenu.showScore(points);
            stage.getScene().setRoot(gameOverMenu.getRoot());
        }
    }

    public int getSpaceshipChoice() {
        return spaceshipChoice;
    }
}
