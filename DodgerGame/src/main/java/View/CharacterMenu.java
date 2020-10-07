package View;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

public class CharacterMenu extends AbstractMenu {

    private MenuButton spaceshipLighterBtn;
    private MenuButton spaceshipTurtleBtn;
    private MenuButton spaceshipThorBtn;
    private MenuButton spaceshipUfoBtn;
    private MenuButton startBtn;
    private MenuButton returnBtn;

    public CharacterMenu() throws IOException {
        // Creates a title to the Character page.
        getGc().fillText("Characters", 160, 100);
        getGc().strokeText("Characters", 160, 100);

        ButtonMenu buttonMenu = new ButtonMenu();
        getRoot().getChildren().addAll(getTitle(), buttonMenu);
    }

    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            HBox characters = new HBox(20);
            VBox startMenu = new VBox(20);

            characters.setTranslateX(165);
            characters.setTranslateY(200);

            startMenu.setTranslateX(270);
            startMenu.setTranslateY(340);

            spaceshipLighterBtn = new MenuButton(generateImage("lighter.gif"));
            spaceshipTurtleBtn = new MenuButton(generateImage("turtle.png"));
            spaceshipThorBtn = new MenuButton(generateImage("thor.gif"));
            spaceshipUfoBtn = new MenuButton(generateImage("ufo.gif"));

            startBtn = new MenuButton("START");
            returnBtn = new MenuButton("BACK");

            characters.getChildren().addAll(spaceshipLighterBtn, spaceshipTurtleBtn, spaceshipThorBtn, spaceshipUfoBtn);
            startMenu.getChildren().addAll(startBtn, returnBtn);
            getChildren().addAll(characters, startMenu);
        }

        private Image generateImage(String name) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
            assert inputStream != null;
            return new Image(inputStream);
        }
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
