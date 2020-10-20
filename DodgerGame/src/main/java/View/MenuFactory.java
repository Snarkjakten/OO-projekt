package View;

import java.io.IOException;

/**
 * @author Tobias Engblom
 */
public abstract class MenuFactory {

    public static CharacterMenu createCharacterMenu() throws IOException {
        return new CharacterMenu();
    }

    public static GameOverMenu createGameOverMenu() throws IOException {
        return new GameOverMenu();
    }

    public static MainMenu createMainMenu() throws IOException {
        return new MainMenu();
    }

    public static PauseMenu createPauseMenu() throws IOException {
        return new PauseMenu();
    }
}
