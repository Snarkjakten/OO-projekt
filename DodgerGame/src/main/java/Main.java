import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author Isak Almeros
 */

public class Main extends Application {
    private Stage stage;
    private  MainMenu mainMenu;
    private Window window;
    private ButtonController buttonController;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        window = new Window();
        mainMenu = new MainMenu();
        buttonController = new ButtonController(window, mainMenu, stage);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = mainMenu.getScene();

        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
