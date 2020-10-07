package View;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;

public class PauseMenu {
    private MenuButton resumeGameBtn;
    private MenuButton restartGameBtn;
    private MenuButton mainMenuBtn;
    private MenuButton quitGameBtn;

    private ButtonMenu buttonMenu = new ButtonMenu();
    private Canvas title;
    private ImageView background;

    private Pane root;

    private int windowWidth = 800;
    private int windowHeight = 600;

    public PauseMenu() throws IOException {
        title = new Canvas(windowWidth, 150);
        GraphicsContext gc = title.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 100);
        gc.setFont(theFont);
        gc.fillText("Characters", 160, 100);
        gc.strokeText("Characters", 160, 100);

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

    public Pane getRoot() {
        return root;
    }
}
