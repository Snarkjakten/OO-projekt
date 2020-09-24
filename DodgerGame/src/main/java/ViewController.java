import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class ViewController {
    private Window window;
    private MainMenu mainMenu;
    private Stage stage;

    public ViewController(Window window, MainMenu mainMenu, Stage stage){
        this.window = window;
        this.mainMenu = mainMenu;
        this.stage = stage;

        mainMenuButtonHandler();
    }

    // Handles button clicks in the main menu
    public void mainMenuButtonHandler(){
        // Starts the game when clicking on "PLAY"
        mainMenu.getPlayBtn().setOnMouseClicked(event -> {
            window.init();
            stage.getScene().setRoot(window.getWin());
        });

        // When clicking on "QUIT"
        mainMenu.getQuitBtn().setOnMouseClicked(event -> {
            closeProgram();
        });

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
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
