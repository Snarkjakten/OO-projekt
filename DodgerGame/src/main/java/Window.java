import Interfaces.IGameOverObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class Window {

    //Creates Pane
    private final Pane root = new Pane();
    private Canvas canvas;
    private GraphicsContext gc;

    public Window(double width, double height) {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
    }

    public void init() {
        try {
            root.getChildren().clear();
            createContent();
            //Adds ImageView and Canvas to Pane
            root.getChildren().addAll(canvas);
        } catch (
                Exception e) {
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
}