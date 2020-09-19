import javafx.stage.Stage;
import javafx.scene.Scene;

public class ButtonController {
    private Window window;
    private Stage stage;

    public ButtonController(Window window, Stage stage){
        this.window = window;
        this.stage = stage;
    }

    // Changes scene to the game scene
    public void changeToGameScene(){
        Scene gameScene = window.getGameScene();
        stage.setScene(gameScene);
    }
}
