package View;


import java.io.IOException;

/**
 * @author Tobias Engblom
 */
public class PauseMenu extends AbstractMenu {
    private final MenuButton resumeGameBtn;
    private final MenuButton restartGameBtn;
    private final MenuButton mainMenuBtn;
    private final MenuButton quitGameBtn;

    public PauseMenu() throws IOException {
        getGraphicsContext().fillText("Characters", 160, 100);
        getGraphicsContext().strokeText("Characters", 160, 100);

        ButtonMenu buttonMenu = new ButtonMenu(20);

        buttonMenu.getvBox().setTranslateX(200);
        buttonMenu.getvBox().setTranslateY(300);

        resumeGameBtn = new MenuButton("RESUME");
        restartGameBtn = new MenuButton("RESTART");
        mainMenuBtn = new MenuButton("MAIN MENU");
        quitGameBtn = new MenuButton("QUIT");

        buttonMenu.getvBox().getChildren().addAll(resumeGameBtn, restartGameBtn, mainMenuBtn, quitGameBtn);

        getRoot().getChildren().addAll(getTitle(), buttonMenu);
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
