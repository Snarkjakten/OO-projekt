package View;


import javafx.scene.image.Image;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author Tobias Engblom
 */
public class CharacterMenu extends AbstractMenu {

    private final MenuButton spaceshipLighterBtn;
    private final MenuButton spaceshipTurtleBtn;
    private final MenuButton spaceshipThorBtn;
    private final MenuButton spaceshipUfoBtn;
    private final MenuButton startBtn;
    private final MenuButton returnBtn;

    public CharacterMenu() throws IOException {
        // Creates a title to the Character page.
        getGraphicsContext().fillText("Characters", 160, 100);
        getGraphicsContext().strokeText("Characters", 160, 100);

        ButtonMenu buttonMenu = new ButtonMenu(20);

        buttonMenu.gethBox().setTranslateX(165);
        buttonMenu.gethBox().setTranslateY(200);

        buttonMenu.getvBox().setTranslateX(270);
        buttonMenu.getvBox().setTranslateY(340);


        startBtn = new MenuButton("START");
        returnBtn = new MenuButton("BACK");
        spaceshipLighterBtn = new MenuButton(generateImage(GameObjectGUI.getFirstChoice()));
        spaceshipTurtleBtn = new MenuButton(generateImage(GameObjectGUI.getSecondChoice()));
        spaceshipThorBtn = new MenuButton(generateImage(GameObjectGUI.getThirdChoice()));
        spaceshipUfoBtn = new MenuButton(generateImage(GameObjectGUI.getFourthChoice()));

        buttonMenu.gethBox().getChildren().addAll(spaceshipLighterBtn, spaceshipTurtleBtn, spaceshipThorBtn, spaceshipUfoBtn);
        buttonMenu.getvBox().getChildren().addAll(startBtn, returnBtn);

        getRoot().getChildren().addAll(getTitle(), buttonMenu);
    }

    private Image generateImage(String name) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
        assert inputStream != null;
        return new Image(inputStream);
    }

    public MenuButton getSpaceshipLighterBtn() {
        return spaceshipLighterBtn;
    }

    public MenuButton getSpaceshipTurtleBtn() {
        return spaceshipTurtleBtn;
    }

    public MenuButton getSpaceshipThorBtn() {
        return spaceshipThorBtn;
    }

    public MenuButton getSpaceshipUfoBtn() {
        return spaceshipUfoBtn;
    }

    public MenuButton getStartBtn() {
        return startBtn;
    }

    public MenuButton getReturnBtn() {
        return returnBtn;
    }
}
