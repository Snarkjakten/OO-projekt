import View.CharacterMenu;
import View.GameOverMenu;
import View.HighScoreMenu;
import View.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author Isak Almeros
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Window window = new Window(stage);
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        new ViewController(window, mainMenu, highScoreMenu , characterMenu, gameOverMenu, stage);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
