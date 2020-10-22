package Controller;

import Model.PausableAnimationTimer;
import View.*;
import Interfaces.IGameOverObserver;
import javafx.stage.Stage;

/**
 * @author Isak Almeros
 */

public class ViewController implements IGameOverObserver {
    private final Window window;
    private final MainMenu mainMenu;
    private final HighScoreMenu highScoreMenu;
    private final CharacterMenu characterMenu;
    private final GameOverMenu gameOverMenu;
    private final Stage stage;
    private int spaceshipChoice;
    private final GameObjectGUI gameObjectGUI;
    private final PausableAnimationTimer gameLoop;

    public ViewController(Window window, MainMenu mainMenu, HighScoreMenu highScoreMenu, CharacterMenu characterMenu,
                          GameOverMenu gameOverMenu, Stage stage, PausableAnimationTimer gameLoop, GameObjectGUI gameObjectGUI) {
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

        mainMenu.getHighScoreBtn().setOnMouseClicked(event -> {
            highScoreMenu.updateScore();
            stage.getScene().setRoot(highScoreMenu.getRoot());
        });

        // When clicking on "QUIT"
        mainMenu.getQuitBtn().setOnMouseClicked(event -> System.exit(0));
    }

    private void highScoreButtonHandler() {
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

    @Override
    public void actOnGameOverEvent(boolean isGameOver, int points) {
        if (isGameOver) {
            gameOverMenu.showScore(points);
            stage.getScene().setRoot(gameOverMenu.getRoot());
        }
    }
}
