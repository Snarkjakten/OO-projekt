package Controller;

import Model.GameWorld;
import javafx.scene.input.KeyEvent;

public class KeyController {

    /**
     * Sets the direction the player wants the ship to move in when the game loop updates.
     *
     * @param event the pressed key
     * @author Irja Vuorela
     */
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                GameWorld.getInstance().getSpaceship().setUp(1);
                break;
            case DOWN:
                GameWorld.getInstance().getSpaceship().setDown(1);
                break;
            case LEFT:
                GameWorld.getInstance().getSpaceship().setLeft(1);
                break;
            case RIGHT:
                GameWorld.getInstance().getSpaceship().setRight(1);
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
                GameWorld.getInstance().getSpaceship().setUp(0);
                break;
            case DOWN:
                GameWorld.getInstance().getSpaceship().setDown(0);
                break;
            case LEFT:
                GameWorld.getInstance().getSpaceship().setLeft(0);
                break;
            case RIGHT:
                GameWorld.getInstance().getSpaceship().setRight(0);
                break;
            default:
                break;
        }
    }
}
