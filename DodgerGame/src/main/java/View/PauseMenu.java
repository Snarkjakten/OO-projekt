package View;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;


import java.io.IOException;


public class PauseMenu extends AbstractMenu {
    private MenuButton resumeGameBtn;
    private MenuButton restartGameBtn;
    private MenuButton mainMenuBtn;
    private MenuButton quitGameBtn;

    public PauseMenu() throws IOException {
        getGc().fillText("Characters", 160, 100);
        getGc().strokeText("Characters", 160, 100);

        ButtonMenu buttonMenu = new ButtonMenu();
        getRoot().getChildren().addAll(getTitle(), buttonMenu);
    }

    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox pauseMenu = new VBox(20);

            pauseMenu.setTranslateX(200);
            pauseMenu.setTranslateY(300);

            resumeGameBtn = new MenuButton("RESUME");
            restartGameBtn = new MenuButton("RESTART");
            mainMenuBtn = new MenuButton("MAIN MENU");
            quitGameBtn = new MenuButton("QUIT");

            getChildren().addAll(pauseMenu);
        }
    }

    public MenuButton getResumeGameBtn() {
        return resumeGameBtn;
    }

    public MenuButton getRestartGameBtn() {
        return restartGameBtn;
    }

    public MenuButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    public MenuButton getQuitGameBtn() {
        return quitGameBtn;
    }
}
