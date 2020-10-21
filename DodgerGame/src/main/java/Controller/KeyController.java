package Controller;

import Model.GameWorld;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyController {

    // Stage is pausing the simulation
    private final Stage stage;

    public KeyController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the direction the player wants the ship to move in when the game loop updates
     *
     * @param event The pressed key
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
            case ESCAPE:
                // TODO Implement pause function and pause menu
                //gameLoop.pause();
                //stage.getScene().setRoot(pauseMenu.getRoot());
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
