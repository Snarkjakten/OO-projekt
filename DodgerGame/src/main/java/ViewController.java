import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class ViewController {
    private Window window;
    private GameOverMenu gameOverMenu;
    private Stage stage;

    private SimpleIntegerProperty hp;

    public ViewController(Window window, GameOverMenu gameOverMenu, Stage stage){
        this.window = window;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;

        hp = window.spaceship.getHp();

        // Listens to changes in hp and stops animationtimer when hp reaches 0, and switches to the game over menu
        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if ((int) newValue < 1){

                    stage.getScene().setOnMouseClicked(null);
                    stage.getScene().setOnKeyPressed(null);
                    stage.getScene().setOnKeyReleased(null);

                    window.stopAnimationTimer();
                    int points = window.getPoints();
                    gameOverMenu.showScore(points);
                    stage.getScene().setRoot(gameOverMenu.getRoot());
                }
            }
        };

        hp.addListener(listener);
    }
}
