import Game.Entities.Player.Spaceship;
import View.PauseMenu;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;

public class KeyController {

    private final Stage stage;
    private final List<Spaceship> spaceships;
    private final PausableAnimationTimer gameLoop;
    private final PauseMenu pauseMenu;

    KeyController(Stage stage, List<Spaceship> spaceships, PausableAnimationTimer gameLoop, PauseMenu pauseMenu) {
        this.spaceships = spaceships;
        this.gameLoop = gameLoop;
        this.stage = stage;
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
}
