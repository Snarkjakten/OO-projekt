import Entities.Ship;
import javafx.scene.input.KeyEvent;

public class KeyController {

    Ship ship;

    KeyController(Ship ship) {
        this.ship = ship;
    }

    // When an arrow key is pressed, the ship moves in that direction
    // @Author Irja Vuorela
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                ship.setUp(1);
                ship.move();
                break;
            case DOWN:
                ship.setDown(1);
                ship.move();
                break;
            case LEFT:
                ship.setLeft(1);
                ship.move();
                break;
            case RIGHT:
                ship.setRight(1);
                ship.move();
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
                ship.setUp(0);
                break;
            case DOWN:
                ship.setDown(0);
                break;
            case LEFT:
                ship.setLeft(0);
                break;
            case RIGHT:
                ship.setRight(0);
                break;
            default:
                break;
        }
    }
}
