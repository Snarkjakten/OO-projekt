import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainMenu {

    private MenuButton playBtn = new MenuButton("PLAY");
    private MenuButton highscoreBtn = new MenuButton("HIGHSCORE");
    private MenuButton quitBtn = new MenuButton("QUIT");

    private ButtonMenu buttonMenu = new ButtonMenu();
    private Canvas title;
    private ImageView background;

    private ButtonController buttonController;

    public MainMenu(ButtonController buttonController) throws IOException {
        this.buttonController = buttonController;
        // Creates a title to the main page.
        Canvas canvas = new Canvas(800, 150);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 100);
        gc.setFont(theFont);
        gc.fillText("Space Dodger", 100, 100);
        gc.strokeText("Space Dodger", 100, 100);
        title = canvas;

        // Adds a background to the main page.
        InputStream is = getClass().getClassLoader().getResourceAsStream("backgroundSpace_01.1.png");
        Image image = new Image(is);
        is.close();

        background = new ImageView(image);
        background.setFitWidth(1200);
        background.setFitHeight(800);
    }

    // Creates a scene for the main page, containing background, buttons and title
    public Scene getScene() throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800,600);

        root.getChildren().addAll(background, buttonMenu, title);

        Scene mainMenuScene = new Scene(root);

        return mainMenuScene;
    }

    // This menu contains the buttons on the main page.
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(270);
            menu.setTranslateY(200);

            // Handle button click
            playBtn.setOnMouseClicked(event -> {
                buttonController.changeToGameScene();
            });

            quitBtn.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu.getChildren().addAll(playBtn, highscoreBtn, quitBtn);
            getChildren().addAll(menu);
        }
    }
}
