import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author Isak Almeros
 */

public class Main extends Application {
    private Window window;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private ViewController viewController;

    @Override
    public void start(Stage stage) throws Exception {
        window = new Window(stage);
        mainMenu = new MainMenu(window, stage);
        gameOverMenu = new GameOverMenu(window, mainMenu, stage);
        viewController = new ViewController(window, gameOverMenu, stage);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        // when closing window in the upper left corner
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Opens a dialog box that asks if you are sure that you want to quit
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
