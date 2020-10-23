package Controller;

import View.*;
import Interfaces.IGameOverObserver;
import View.GUI.GameObjectGUI;
import View.Menu.CharacterMenu;
import View.Menu.GameOverMenu;
import View.Menu.HighScoreMenu;
import View.Menu.MainMenu;
import javafx.stage.Stage;

public class ViewController implements IGameOverObserver {
    private final GameWindow gameWindow;
    private final MainMenu mainMenu;
    private final HighScoreMenu highScoreMenu;
    private final CharacterMenu characterMenu;
    private final GameOverMenu gameOverMenu;
    private final Stage stage;
    private int spaceshipChoice;
    private final GameObjectGUI gameObjectGUI;
    private final AnimationController gameLoop;

    /**
     * @param gameWindow    the window that appears when the round of play is started
     * @param mainMenu      the main menu
     * @param highScoreMenu the menu where the player can see the highScores
     * @param characterMenu the menu where the player choose spaceship
     * @param gameOverMenu  the menu where the player is redirected to if he or she loses
     * @param stage         the stage of the Application
     * @param gameLoop      the gameLoop of the current game
     * @param gameObjectGUI the GUI where every game object is drawn
     */
    public ViewController(GameWindow gameWindow, MainMenu mainMenu, HighScoreMenu highScoreMenu, CharacterMenu characterMenu,
                          GameOverMenu gameOverMenu, Stage stage, AnimationController gameLoop, GameObjectGUI gameObjectGUI) {
        this.gameWindow = gameWindow;
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

    /**
     * Handles button clicks in the main menu
     *
     * @author Isak Almeros
     */
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

    /**
     * Handles button clicks in the highScore menu
     *
     * @author Irja Vuorela
     */
    private void highScoreButtonHandler() {
        highScoreMenu.getMainMenuBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    /**
     * Handles button clicks in the character menu
     *
     * @author Tobias Engblom
     */
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
                stage.getScene().setRoot(gameWindow.getRoot());
                gameLoop.startAnimationLoop();
                gameWindow.init();
            }
        });

        characterMenu.getReturnBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    /**
     * Handles button clicks in the game over menu
     *
     * @author Irja Vuorela
     */
    private void gameOverButtonHandler() {
        gameOverMenu.getTryAgainBtn().setOnMouseClicked(event -> {
            gameLoop.startAnimationLoop();
            stage.getScene().setRoot(gameWindow.getRoot());
            gameWindow.init();
        });

        gameOverMenu.getMainMenuBtn().setOnMouseClicked(event -> stage.getScene().setRoot(mainMenu.getRoot()));
    }

    /**
     * Sets scene to game over menu if game over and shows the points the player got
     *
     * @param isGameOver if the game is over
     * @param points     the points the player got during the last round of play
     */
    @Override
    public void actOnGameOverEvent(boolean isGameOver, int points) {
        if (isGameOver) {
            gameOverMenu.showScore(points);
            stage.getScene().setRoot(gameOverMenu.getRoot());
        }
    }
}
