import View.*;
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
        MainMenu mainMenu = MenuFactory.createMainMenu();
        CharacterMenu characterMenu = MenuFactory.createCharacterMenu();
        PauseMenu pauseMenu = MenuFactory.createPauseMenu();
        GameOverMenu gameOverMenu = MenuFactory.createGameOverMenu();
        new ViewController(window, mainMenu, characterMenu, pauseMenu, gameOverMenu, stage);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        PausableAnimationTimer pausableAnimationTimer = new PausableAnimationTimer() {
            @Override
            public void tick(long currentNanoTime) {

            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
