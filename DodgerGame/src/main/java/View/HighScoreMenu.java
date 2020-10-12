package View;

import Score.HighScoreHandler;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HighScoreMenu {

    private Pane root;
    private MenuButton mainMenuBtn;
    private HighScoreMenu.ButtonMenu buttonMenu;
    private StringBuilder sb;
    private Text scores;

    public HighScoreMenu() throws IOException {

        // Creates a title to the page.
        Canvas title = new Canvas(800, 200);
        GraphicsContext gc = title.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 80);
        gc.setFont(theFont);
        gc.fillText("High Scores", 150, 100);
        gc.strokeText("High Scores", 150, 100);

        buttonMenu = new HighScoreMenu.ButtonMenu();

        // Presents the scores
        scores = new Text();
        HighScoreHandler hs = new HighScoreHandler();
        scores.setText(presentableScores(hs.getScoresFromFile(hs.getFileName())));
        Font theFont2 = Font.font("Arial", FontWeight.BOLD, 30);
        scores.setFont(theFont2);
        scores.setFill(Color.GRAY);
        scores.setStroke(Color.WHITE);
        scores.setTranslateX(280);
        scores.setTranslateY(180);

        // Adds a background to the screen.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("bg_1_1.png");
        Image image = new Image(inputStream);
        inputStream.close();

        ImageView background = new ImageView(image);
        background.setFitWidth(800);
        background.setFitHeight(600);

        root = new Pane();
        root.setPrefSize(800, 600);

        root.getChildren().addAll(background, title, scores, buttonMenu);
    }

    // Menu that contains the buttons on the screen
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(270);
            menu.setTranslateY(530);

            mainMenuBtn = new MenuButton("BACK");

            menu.getChildren().addAll(mainMenuBtn);
            getChildren().addAll(menu);
        }
    }

    public Pane getRoot() {
        return root;
    }

    public MenuButton getMainMenuBtn() {
        return mainMenuBtn;
    }


    /**
     * Turns list of scores into a presentable format for this view to use.
     *
     * @param scores list of scores
     * @return scores as a string with a new line per score.
     * @author Irja Vuorela
     */
    public String presentableScores(List<Integer> scores) {
        if (scores.isEmpty()) {
            return "No high scores yet!";
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (int s : scores) {
                sb.append(i + ": " + s);
                sb.append("\n");
                i++;
            }
            return sb.toString();
        }
    }
}
