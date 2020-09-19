import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author Isak Almeros
 */

public class Main extends Application {
    Stage stage;
    MainMenu mainMenu;
    Window window;
    ButtonController buttonController;

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        window = new Window();
        buttonController = new ButtonController(window, stage);
        mainMenu = new MainMenu(buttonController);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = mainMenu.getScene();

        stage.setScene(mainMenuScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
