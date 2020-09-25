import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class KeyController {

    HashMap<String, Spaceship> spaceships;

    KeyController(HashMap<String, Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    // When an arrow key is pressed, the ship moves in that direction
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                spaceships.forEach((key, spaceship) -> moveUp(spaceship));
                break;
            case DOWN:
                spaceships.forEach((key, spaceship) -> moveDown(spaceship));
                break;
            case LEFT:
                spaceships.forEach((key, spaceship) -> moveLeft(spaceship));
                break;
            case RIGHT:
                spaceships.forEach((key, spaceship) -> moveRight(spaceship));
                break;
            default:
                break;
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
        switch (event.getCode()) {
            case UP:
                spaceships.forEach((key, spaceship) -> spaceship.setUp(0));
                break;
            case DOWN:
                spaceships.forEach((key, spaceship) -> spaceship.setDown(0));
                break;
            case LEFT:
                spaceships.forEach((key, spaceship) -> spaceship.setLeft(0));
                break;
            case RIGHT:
                spaceships.forEach((key, spaceship) -> spaceship.setRight(0));
                break;
            default:
                break;
        }
    }
}
