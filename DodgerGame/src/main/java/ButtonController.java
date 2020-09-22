import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class ButtonController {
    private Window window;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private Stage stage;

    public ButtonController(Window window, MainMenu mainMenu, GameOverMenu gameOverMenu, Stage stage){
        this.window = window;
        this.mainMenu = mainMenu;
        this.gameOverMenu = gameOverMenu;
        this.stage = stage;

        mainMenuButtonHandler();
        gameOverButtonHandler();
    }

    // Handles button clicks in the main menu
    private void mainMenuButtonHandler(){
        mainMenu.getPlayBtn().setOnMouseClicked(event -> {
            Scene gameScene = window.getGameScene();
            stage.setScene(gameScene);
        });

        mainMenu.getHighscoreBtn().setOnMouseClicked(event -> {
            Scene gameOverScene = gameOverMenu.getGameOverScene();
            stage.setScene(gameOverScene);
        });

        mainMenu.getQuitBtn().setOnMouseClicked(event -> {
            closeProgram();
        });

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
    }

    private void gameOverButtonHandler(){
        gameOverMenu.getMainMenu().setOnMouseClicked(event -> {
            Scene mainMenuScene = null;
            try {
                mainMenuScene = mainMenu.getScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(mainMenuScene);
        });
    }

    // Opens a dialog box when pressing quit
    private void closeProgram(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit game");
        alert.setContentText("Are you sure you want to quit?");

        // Replace buttons
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }
}
