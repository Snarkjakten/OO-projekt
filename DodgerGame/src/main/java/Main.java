import View.CharacterMenu;
import View.GameOverMenu;
import View.MainMenu;
import View.SoundHandler;
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
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        new ViewController(window, mainMenu, characterMenu, gameOverMenu, stage);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        String backgroundMusicPath = "src/main/resources/277325__shadydave__time-break (1).wav";
        SoundHandler soundHandler = new SoundHandler();
        soundHandler.soundPlayer(backgroundMusicPath, 0.25);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
