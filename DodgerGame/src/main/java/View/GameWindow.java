package View;

import Model.GameWorld;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class GameWindow {

    //Creates Pane
    private final Pane root = new Pane();
    private final Canvas canvas;
    private final GraphicsContext gc;

    public GameWindow(double width, double height) {
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
        root.setPrefSize(GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight());
    }

    public Pane getRoot() {
        return root;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}