import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyController {

    List<Spaceship> spaceships;

    KeyController(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
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
                    //pauseGame();
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
