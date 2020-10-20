package Controller;

import Interfaces.IGameWorldObserver;
import Model.GameWorld;
import Model.PausableAnimationTimer;
import View.PauseMenu;
import Model.Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyController implements IGameWorldObserver {

    private final Stage stage;
    private GameWorld gameWorld;
    private Spaceship spaceship;
    private final PausableAnimationTimer gameLoop;
    private final PauseMenu pauseMenu;

    public KeyController(Stage stage, PausableAnimationTimer gameLoop, PauseMenu pauseMenu) {
        this.gameLoop = gameLoop;
        this.stage = stage;
        this.gameWorld = GameWorld.getInstance();
        this.spaceship = gameWorld.getSpaceship();
        this.pauseMenu = pauseMenu;
    }

    /**
     * Sets the direction the player wants the ship to move in when the game loop updates
     *
     * @param event The pressed key
     * @author Irja Vuorela
     */
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                spaceship.setUp(1);
                break;
            case DOWN:
                spaceship.setDown(1);
                break;
            case LEFT:
                spaceship.setLeft(1);
                break;
            case RIGHT:
                spaceship.setRight(1);
                break;
            case ESCAPE:
                // TODO Implement pause function and pause menu
                //gameLoop.pause();
                //stage.getScene().setRoot(pauseMenu.getRoot());
                break;
            default:
                break;
        }
    }

    /**
     * Sets the direction the player doesn't want the ship to move in when the game loop updates
     *
     * @param event The released key
     * @author Irja Vuorela
     */
    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                spaceship.setUp(0);
                break;
            case DOWN:
                spaceship.setDown(0);
                break;
            case LEFT:
                spaceship.setLeft(0);
                break;
            case RIGHT:
                spaceship.setRight(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void actOnEvent() {
        gameWorld = GameWorld.getInstance();
        spaceship = gameWorld.getSpaceship();
    }
}
