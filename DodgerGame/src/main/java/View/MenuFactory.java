package View;

import java.io.IOException;

/**
 * @author Tobias Engblom
 */
public abstract class MenuFactory {

    public static AbstractMenu createCharacterMenu() throws IOException {
        return new CharacterMenu();
    }

    public static AbstractMenu createGameOverMenu() throws IOException {
        return new GameOverMenu();
    }

    public static AbstractMenu createMainMenu() throws IOException {
        return new MainMenu();
    }

    public static AbstractMenu createPauseMenu() throws IOException {
        return new PauseMenu();
    }

    public static AbstractMenu createHighScoreMenu() throws IOException {
        return new HighScoreMenu();
    }
}
