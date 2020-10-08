package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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

/**
 * @Author Isak Almeros
 */

public class GameOverMenu {

    private Pane root;
    private MenuButton mainMenuBtn;
    private MenuButton tryAgainBtn;
    private GameOverMenu.ButtonMenu buttonMenu;
    private StringBuilder sb;
    private Text score;

    public GameOverMenu() throws IOException {
        // Creates a title to the page.
        Canvas title = new Canvas(800, 200);
        GraphicsContext gc = title.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 80);
        gc.setFont(theFont);
        gc.fillText("GAME OVER", 150, 100);
        gc.strokeText("GAME OVER", 150, 100);

        sb = new StringBuilder("Score: ");
        buttonMenu = new GameOverMenu.ButtonMenu();

        // Presents the score
        score = new Text();
        score.setText(sb.toString());
        Font theFont2 = Font.font("Arial", FontWeight.BOLD, 30);
        score.setFont(theFont2);
        score.setFill(Color.GRAY);
        score.setStroke(Color.WHITE);
        score.setTranslateX(310);
        score.setTranslateY(200);

        // Adds a background to the screen.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("bg_1_1.png");
        Image image = new Image(inputStream);
        inputStream.close();

        ImageView background = new ImageView(image);
        background.setFitWidth(800);
        background.setFitHeight(600);

        root = new Pane();
        root.setPrefSize(800,600);

        root.getChildren().addAll(background, title, score, buttonMenu);
    }

    // Menu that contains the buttons on the screen
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(270);
            menu.setTranslateY(250);

            tryAgainBtn = new MenuButton("TRY AGAIN");
            mainMenuBtn = new MenuButton("MAIN MENU");

            menu.getChildren().addAll(tryAgainBtn, mainMenuBtn);
            getChildren().addAll(menu);
        }
    }

    public Pane getRoot() {
        return root;
    }

    public MenuButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    public MenuButton getTryAgainBtn() {
        return tryAgainBtn;
    }

    // Adds score to the game over menu
    public void showScore(int points){
        sb.replace(0, sb.length() - 1, "Score: " + String.valueOf(points));
        score.setText(sb.toString());
    }
}
