import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyController {

    List<Spaceship> spaceships;

    KeyController(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    // When an arrow key is pressed, the ship moves in that direction
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        for (Spaceship spaceship : spaceships) {
            switch (event.getCode()) {
                case UP:
                    moveUp(spaceship);
                    break;
                case DOWN:
                    moveDown(spaceship);
                    break;
                case LEFT:
                    moveLeft(spaceship);
                    break;
                case RIGHT:
                    moveRight(spaceship);
                    break;
                default:
                    break;
            }
        }
    }

    private void moveUp(Spaceship spaceship) {
        spaceship.setUp(1);
        spaceship.move();
    }

    private void moveDown(Spaceship spaceship) {
        spaceship.setDown(1);
        spaceship.move();
    }

    private void moveLeft(Spaceship spaceship) {
        spaceship.setLeft(1);
        spaceship.move();
    }

    private void moveRight(Spaceship spaceship) {
        spaceship.setRight(1);
        spaceship.move();
    }

    // When an arrow key is released, the ship stops moving in that direction
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
