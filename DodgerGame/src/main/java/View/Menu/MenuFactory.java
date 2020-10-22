package View.Menu;

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

}
