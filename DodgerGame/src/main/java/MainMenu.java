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

/**
 * @Author Isak Almeros
 */
public class MainMenu {

    private MenuButton playBtn;
    private MenuButton highscoreBtn;
    private MenuButton quitBtn;
    MenuButton hp;

    private ButtonMenu buttonMenu = new ButtonMenu();
    private Canvas title;
    private ImageView background;

    private Pane root;

    public MainMenu() throws IOException {
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

    /*
    // Creates a scene for the main page, containing background, buttons and title
    public Scene getScene() throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800,600);

        root.getChildren().addAll(background, buttonMenu, title);

        Scene mainMenuScene = new Scene(root);

        return mainMenuScene;
    }

     */

    // This menu contains the buttons on the main page.
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(270);
            menu.setTranslateY(200);

            playBtn = new MenuButton("PLAY");
            highscoreBtn = new MenuButton("HIGHSCORE");
            quitBtn = new MenuButton("QUIT");

            hp = new MenuButton("HP");

            menu.getChildren().addAll(playBtn, highscoreBtn, quitBtn, hp);
            getChildren().addAll(menu);
        }
    }

    public MenuButton getPlayBtn() {
        return playBtn;
    }

    public MenuButton getHighscoreBtn() {
        return highscoreBtn;
    }

    public MenuButton getQuitBtn() {
        return quitBtn;
    }

    public Pane getRoot() {
        return root;
    }

    public MenuButton getHp() {
        return hp;
    }
}
