package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Tobias Engblom
 */
public abstract class AbstractMenu {
    private final Canvas title;
    private final GraphicsContext graphicsContext;
    private final Pane root;

    public AbstractMenu() throws IOException {
        int windowWidth = 800;
        title = new Canvas(windowWidth, 200);
        graphicsContext = title.getGraphicsContext2D();
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 100);
        graphicsContext.setFont(font);
        graphicsContext.setFill(Color.BLUEVIOLET);
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setLineWidth(2);

        InputStream is = getClass().getClassLoader().getResourceAsStream("backgroundSpace_01.1.png");
        assert is != null;
        Image image = new Image(is);
        is.close();

        // Adds background to Menu
        ImageView background = new ImageView(image);
        background.setFitWidth(windowWidth);
        int windowHeight = 600;
        background.setFitHeight(windowHeight);

        root = new Pane();
        root.setPrefSize(windowWidth, windowHeight);
        root.getChildren().addAll(background);
    }

    /**
     * @return the Pane for this Menu
     * @author Tobias Engblom
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * @return the Title for this Menu
     * @author Tobias Engblom
     */
    public Canvas getTitle() {
        return title;
    }

    /**
     * @return the GraphicsContext for this Menu
     * @author Tobias Engblom
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }
}
