package View;


import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @Author Isak Almeros
 */

public class GameOverMenu extends AbstractMenu {

    private MenuButton mainMenuBtn;
    private MenuButton tryAgainBtn;
    private final StringBuilder sb;
    private final Text score;

    public GameOverMenu() throws IOException {
        // Creates a title to the page.
        Font theFont = Font.font("Arial", FontWeight.BOLD, 80);
        getGc().setFont(theFont);
        getGc().fillText("GAME OVER", 150, 100);
        getGc().strokeText("GAME OVER", 150, 100);

        sb = new StringBuilder("Score: ");
        ButtonMenu buttonMenu = new ButtonMenu();

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
