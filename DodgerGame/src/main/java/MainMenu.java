import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends Application {

    private MenuButton playBtn = new MenuButton("PLAY");
    private MenuButton highscoreBtn = new MenuButton("HIGHSCORE");
    private MenuButton quitBtn = new MenuButton("QUIT");

    private ButtonMenu buttonMenu;

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Space Dodger");

        Pane root = new Pane();
        root.setPrefSize(1200,800);

        ImageView background = addMainMenuBackground();
        buttonMenu = new ButtonMenu();
        Canvas title = addTitle();

        root.getChildren().addAll(background, buttonMenu, title);

        Scene mainMenuScene = new Scene(root);

        primaryStage.setScene(mainMenuScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Creates a title to the main page.
    private Canvas addTitle(){
        Canvas canvas = new Canvas(1200, 150);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 100);
        gc.setFont(theFont);
        gc.fillText("Space Dodger", 300, 100);
        gc.strokeText("Space Dodger", 300, 100);

        return canvas;
    }

    // Adds a background to the main page.
    private ImageView addMainMenuBackground() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("backgroundSpace_01.1.png");
        Image image = new Image(is);
        is.close();

        ImageView background = new ImageView(image);
        background.setFitWidth(1200);
        background.setFitHeight(800);

        return background;
    }

    // This menu contains the buttons on the main page.
    private class ButtonMenu extends Parent {
        public ButtonMenu() {
            VBox menu = new VBox(20);

            menu.setTranslateX(450);
            menu.setTranslateY(200);

            playBtn.setOnMouseClicked(Event -> {
                //primaryStage.setScene(newScene);
            });

            quitBtn.setOnMouseClicked(Event -> {
                System.exit(0);
            });

            menu.getChildren().addAll(playBtn, highscoreBtn, quitBtn);

            getChildren().addAll(menu);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
