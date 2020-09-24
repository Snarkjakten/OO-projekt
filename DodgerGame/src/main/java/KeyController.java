import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyController {

    List<Spaceship> spaceships;

    KeyController(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    // When an arrow key is pressed, the ship moves in that direction
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                for (Spaceship spaceship : spaceships) {
                    spaceship.setUp(1);
                    spaceship.move();
                }
                break;
            case DOWN:
                for (Spaceship spaceship : spaceships) {
                    spaceship.setDown(1);
                    spaceship.move();
                }
                break;
            case LEFT:
                for (Spaceship spaceship : spaceships) {
                    spaceship.setLeft(1);
                    spaceship.move();
                }
                break;
            case RIGHT:
                for (Spaceship spaceship : spaceships) {
                    spaceship.setRight(1);
                    spaceship.move();
                }
                break;
            default:
                break;
        }
    }

    // When an arrow key is released, the ship stops moving in that direction
    // @Author Irja Vuorela
    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                for (Spaceship spaceship : spaceships)
                spaceship.setUp(0);
                break;
            case DOWN:
                for (Spaceship spaceship : spaceships)
                spaceship.setDown(0);
                break;
            case LEFT:
                for (Spaceship spaceship : spaceships)
                spaceship.setLeft(0);
                break;
            case RIGHT:
                for (Spaceship spaceship : spaceships)
                spaceship.setRight(0);
                break;
            default:
                break;
        }
    }
}
