package View.Menu;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Isak Almeros
 */

public class GameOverMenu extends AbstractMenu {

    private final MenuButton mainMenuBtn;
    private final MenuButton tryAgainBtn;
    private final StringBuilder sb;
    private final Text score;

    public GameOverMenu() throws IOException {
        // Creates a title to the page.
        getGraphicsContext().fillText("GAME OVER", 95, 100);
        getGraphicsContext().strokeText("GAME OVER", 95, 100);

        ButtonMenu buttonMenu = new ButtonMenu(20);

        buttonMenu.getvBox().setTranslateX(270);
        buttonMenu.getvBox().setTranslateY(250);

        tryAgainBtn = new MenuButton("TRY AGAIN");
        mainMenuBtn = new MenuButton("MAIN MENU");

        buttonMenu.getvBox().getChildren().addAll(tryAgainBtn, mainMenuBtn);

        sb = new StringBuilder("Score: ");

        // Presents the score
        score = new Text();
        score.setText(sb.toString());
        Font theFont2 = Font.font("Arial", FontWeight.BOLD, 30);
        score.setFont(theFont2);
        score.setFill(Color.GRAY);
        score.setStroke(Color.WHITE);
        score.setTranslateX(310);
        score.setTranslateY(200);

        getRoot().getChildren().addAll(getTitle(), score, buttonMenu);
    }

    public MenuButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    public MenuButton getTryAgainBtn() {
        return tryAgainBtn;
    }

    // Adds score to the game over menu
    public void showScore(int points) {
        sb.replace(0, sb.length() - 1, "Score: " + points);
        score.setText(sb.toString());
    }
}
