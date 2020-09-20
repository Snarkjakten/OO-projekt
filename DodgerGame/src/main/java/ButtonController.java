import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * @Author Isak Almeros
 */
public class ButtonController {
    private Window window;
    private MainMenu mainMenu;
    private Stage stage;

    public ButtonController(Window window, MainMenu mainMenu, Stage stage){
        this.window = window;
        this.mainMenu = mainMenu;
        this.stage = stage;

        mainMenuButtonHandler();
    }

    // Handles button clicks in the main menu
    public void mainMenuButtonHandler(){
        mainMenu.getPlayBtn().setOnMouseClicked(event -> {
            Scene gameScene = window.getGameScene();
            stage.setScene(gameScene);
        });

        mainMenu.getQuitBtn().setOnMouseClicked(event -> {
            System.exit(0);
        });

    }
}
