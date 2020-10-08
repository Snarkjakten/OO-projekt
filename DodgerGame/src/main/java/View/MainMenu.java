package View;


import java.io.IOException;

/**
 * @author Isak Almeros
 */
public class MainMenu extends AbstractMenu {

    private final MenuButton playBtn;
    private final MenuButton highscoreBtn;
    private final MenuButton quitBtn;

    public MainMenu() throws IOException {
        // Creates a title to the main page.
        getGc().fillText("Space Dodger", 100, 100);
        getGc().strokeText("Space Dodger", 100, 100);

        ButtonMenu buttonMenu = new ButtonMenu(20);

        buttonMenu.getvBox().setTranslateX(270);
        buttonMenu.getvBox().setTranslateY(200);

        playBtn = new MenuButton("PLAY");
        highscoreBtn = new MenuButton("HIGHSCORE");
        quitBtn = new MenuButton("QUIT");

        buttonMenu.getvBox().getChildren().addAll(playBtn, highscoreBtn, quitBtn);
        getRoot().getChildren().addAll(buttonMenu, getTitle());
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
