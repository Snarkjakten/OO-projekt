import javafx.scene.Parent;
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

/**
 * @Author Isak Almeros
 */
public class MainMenu {

    private MenuButton playBtn;
    private MenuButton highscoreBtn;
    private MenuButton quitBtn;

    private ButtonMenu buttonMenu = new ButtonMenu();
    private Canvas title;
    private ImageView background;

    private Pane root;
    private Window window;
    private Stage stage;

    public MainMenu(Window window, Stage stage) throws IOException {
        this.window = window;
        this.stage = stage;

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
        background.setFitWidth(800);
        background.setFitHeight(600);

        root = new Pane();
        root.setPrefSize(800,600);

        root.getChildren().addAll(background, buttonMenu, title);
    }

    // This menu contains the buttons on the main page.
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(270);
            menu.setTranslateY(200);

            playBtn = new MenuButton("PLAY");
            highscoreBtn = new MenuButton("HIGHSCORE");
            quitBtn = new MenuButton("QUIT");

            // Starts the game when clicking on "PLAY"
            playBtn.setOnMouseClicked(event -> {
                window.init();
                stage.getScene().setRoot(window.getRoot());
            });


            // TODO: 2020-09-27 go to highscore menu
            highscoreBtn.setOnMouseClicked(event -> {
                // stage.getScene().setRoot(gameOverMenu.getRoot());
            });

            // When clicking on "QUIT"
            quitBtn.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu.getChildren().addAll(playBtn, highscoreBtn, quitBtn);
            getChildren().addAll(menu);
        }
    }

    public Pane getRoot() {
        return root;
    }
}
