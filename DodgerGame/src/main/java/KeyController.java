import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class KeyController {

    HashMap<String, Spaceship> spaceships;

    KeyController(HashMap<String, Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    // Sets the direction the player wants the ship to move in when the game loop updates
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                spaceships.forEach((key, spaceship) -> spaceship.setUp(1));
                break;
            case DOWN:
                spaceships.forEach((key, spaceship) -> spaceship.setDown(1));
                break;
            case LEFT:
                spaceships.forEach((key, spaceship) -> spaceship.setLeft(1));
                break;
            case RIGHT:
                spaceships.forEach((key, spaceship) -> spaceship.setRight(1));
                break;
            default:
                break;
        }
    }

    // // Sets the direction the player doesn't want the ship to move in when the game loop updates
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
