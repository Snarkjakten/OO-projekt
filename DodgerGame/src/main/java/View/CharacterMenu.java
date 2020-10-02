package View;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;

public class CharacterMenu {

    private MenuButton spaceshipLighterBtn;
    private MenuButton spaceshipTurtleBtn;
    private MenuButton spaceshipThorBtn;
    private MenuButton spaceshipUfoBtn;
    private MenuButton startBtn;
    private MenuButton returnBtn;

    private ButtonMenu buttonMenu = new ButtonMenu();
    private Canvas title;
    private ImageView background;

    private Pane root;

    private int windowWidth = 800;
    private int windowHeight = 600;

    public CharacterMenu() throws IOException {
        // Creates a title to the Character page.
        title = new Canvas(windowWidth, 150);
        GraphicsContext gc = title.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 100);
        gc.setFont(theFont);
        gc.fillText("Characters", 160, 100);
        gc.strokeText("Characters", 160, 100);

        // Adds a background to the Character page.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("backgroundSpace_01.1.png");
        Image image = new Image(inputStream);
        inputStream.close();

        background = new ImageView(image);
        background.setFitWidth(windowWidth);
        background.setFitHeight(windowHeight);

        root = new Pane();
        root.setPrefSize(windowWidth, windowHeight);

        root.getChildren().addAll(background, buttonMenu, title);
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
            Image spaceshipImage = new Image(inputStream);
            return spaceshipImage;
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

    public Pane getRoot() {
        return root;
    }
}
