package View.Menu;

import Model.HighScoreHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HighScoreMenu extends AbstractMenu {

    private final MenuButton mainMenuBtn;
    private final Text scores;
    private final HighScoreHandler hs = new HighScoreHandler();

    public HighScoreMenu() throws IOException {

        // Creates a title to the page.
        getGraphicsContext().setFill(Color.GRAY);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 80);
        getGraphicsContext().setFont(theFont);
        getGraphicsContext().fillText("High Scores", 150, 100);
        getGraphicsContext().strokeText("High Scores", 150, 100);

        ButtonMenu buttonMenu = new ButtonMenu(20);

        buttonMenu.getvBox().setTranslateX(270);
        buttonMenu.getvBox().setTranslateY(530);

        mainMenuBtn = new MenuButton("BACK");

        // Presents the scores
        HighScoreHandler hs = new HighScoreHandler();
        scores = new Text();
        scores.setText(presentableScores(hs.getScoresFromFile(hs.getFileName())));
        Font theFont2 = Font.font("Arial", FontWeight.BOLD, 30);
        scores.setFont(theFont2);
        scores.setFill(Color.GRAY);
        scores.setStroke(Color.WHITE);
        scores.setTranslateX(280);
        scores.setTranslateY(180);

        // Adds a background to the screen.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("bg_1_1.png");
        assert inputStream != null;
        Image image = new Image(inputStream);
        inputStream.close();

        ImageView background = new ImageView(image);
        background.setFitWidth(800);
        background.setFitHeight(600);

        buttonMenu.getvBox().getChildren().addAll(mainMenuBtn);

        getRoot().getChildren().addAll(background, getTitle(), scores, buttonMenu);
    }

    public void updateScore() {
        scores.setText(presentableScores(hs.getScoresFromFile(hs.getFileName())));
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
                sb.append(i).append(": ").append(s);
                sb.append("\n");
                i++;
            }
            return sb.toString();
        }
    }
}
