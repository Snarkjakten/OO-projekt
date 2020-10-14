import Interfaces.IGameOverObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window implements IGameOverObserver {

    //Creates Pane
    private final Pane root = new Pane();
    private GameWorld gameWorld = GameWorld.getInstance();
    private final Stage stage;
    private Canvas canvas;
    private GraphicsContext gc;

    public Window(Stage stage, double width, double height) {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.stage = stage;
    }

    public void init() {
        try {
            root.getChildren().clear();
            createContent();

            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);

            /**
             * Handle key pressed
             * @Author Irja Vuorela
             */
            KeyController keyController = new KeyController(gameWorld.getSpaceships());
            stage.getScene().setOnKeyPressed(
                    keyController::handleKeyPressed);

            /**
             * Handle key released
             * @Author Irja Vuorela
             */
            stage.getScene().setOnKeyReleased(
                    keyController::handleKeyReleased
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sets size of Pane
    private void createContent() {
        root.setPrefSize(800, 600);
    }

    public Pane getRoot() {
        return root;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    @Override
    public void actOnEvent(boolean isGameOver) {
        if(isGameOver) {
            gameWorld = GameWorld.getInstance();
        }
    }
}