import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    MainMenu mainMenu = new MainMenu();
    Stage stage;

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
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
