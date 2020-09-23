import Entities.Player.Spaceship;
import javafx.scene.input.KeyEvent;

public class KeyController {

    Spaceship spaceship;

    KeyController(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    // When an arrow key is pressed, the ship moves in that direction
    // @Author Irja Vuorela
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
            default:
                break;
        }
    }

    // When an arrow key is released, the ship stops moving in that direction
    // @Author Irja Vuorela
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
}
