import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;

public class GameOverMenu {

    private Scene gameOverScene;
    private MenuButton mainMenu;

    public GameOverMenu() throws IOException {
        // Creates a title to the page.
        Canvas title = new Canvas(800, 150);
        GraphicsContext gc = title.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 80);
        gc.setFont(theFont);
        gc.fillText("GAME OVER", 150, 100);
        gc.strokeText("GAME OVER", 150, 100);

        Text t = new Text();
        t.setText("Score:");
        Font theFont2 = Font.font("Arial", FontWeight.BOLD, 50);
        t.setFont(theFont2);
        t.setFill(Color.GRAY);
        t.setStroke(Color.WHITE);
        t.setTranslateX(200);


        Canvas score = new Canvas(800, 150);
        GraphicsContext gc2 = score.getGraphicsContext2D();
        gc2.setFill(Color.GRAY);
        gc2.setStroke(Color.WHITE);
        gc2.setLineWidth(2);
        Font theFont3 = Font.font("Arial", FontWeight.BOLD, 50);
        gc2.setFont(theFont3);
        gc2.fillText("SCORE:", 150, 150);
        gc2.strokeText("SCORE:", 150, 150);

        mainMenu = new MenuButton("Main menu");

        VBox box = new VBox(title, t, mainMenu);


        // Adds a background to the main page.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("bg_1_1.png");
        Image image = new Image(inputStream);
        inputStream.close();

        ImageView background = new ImageView(image);
        background.setFitWidth(800);
        background.setFitHeight(600);

        Pane root = new Pane();
        root.setPrefSize(800,600);

        root.getChildren().addAll(background, box);

        gameOverScene = new Scene(root);
    }

    public Scene getGameOverScene() {
        return gameOverScene;
    }

    public MenuButton getMainMenu() {
        return mainMenu;
    }
}
