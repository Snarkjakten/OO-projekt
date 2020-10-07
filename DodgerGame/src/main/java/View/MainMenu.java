package View;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;


import java.io.IOException;

/**
 * @Author Isak Almeros
 */
public class MainMenu extends AbstractMenu {

    private MenuButton playBtn;
    private MenuButton highscoreBtn;
    private MenuButton quitBtn;

    public MainMenu() throws IOException {
        // Creates a title to the main page.
        getGc().fillText("Space Dodger", 100, 100);
        getGc().strokeText("Space Dodger", 100, 100);

        ButtonMenu buttonMenu = new ButtonMenu();
        getRoot().getChildren().addAll(buttonMenu, getTitle());
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

            menu.getChildren().addAll(playBtn, highscoreBtn, quitBtn);
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
}
