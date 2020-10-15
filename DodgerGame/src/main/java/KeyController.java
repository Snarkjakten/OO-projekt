import Game.Entities.Player.Spaceship;
import Interfaces.IGameOverObserver;
import Interfaces.IGameWorldObserver;
import View.PauseMenu;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;

public class KeyController implements IGameWorldObserver {

    private final Stage stage;
    private GameWorld gameWorld;
    private List<Spaceship> spaceships;
    private final PausableAnimationTimer gameLoop;
    private final PauseMenu pauseMenu;

    KeyController(Stage stage, PausableAnimationTimer gameLoop, PauseMenu pauseMenu) {
        this.gameLoop = gameLoop;
        this.stage = stage;
        this.gameWorld = GameWorld.getInstance();
        this.spaceships = gameWorld.getSpaceships();
        this.pauseMenu = pauseMenu;
    }

    // Sets the direction the player wants the ship to move in when the game loop updates
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        for (Spaceship spaceship : spaceships) {
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
                    gameLoop.pause();
                    stage.getScene().setRoot(pauseMenu.getRoot());
                    break;
                default:
                    break;
            }
        }
    }

    // // Sets the direction the player doesn't want the ship to move in when the game loop updates
    // @Author Irja Vuorela
    public void handleKeyReleased(KeyEvent event) {
        for (Spaceship spaceship : spaceships) {
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
    }

    @Override
    public void actOnEvent() {
        gameWorld = GameWorld.getInstance();
        spaceships = gameWorld.getSpaceships();
    }
}
