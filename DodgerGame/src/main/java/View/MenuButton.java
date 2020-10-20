package View;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author Isak Almeros
 */

public class MenuButton extends StackPane {
    private final Rectangle background;
    private Text text;
    private ImageView character;

    public MenuButton(Image image) {
        this.character = new ImageView(image);
        this.character.setFitWidth(64);
        this.character.setFitHeight(64);

        background = new Rectangle(100, 100);
        background.setFill(Color.DARKBLUE);
        background.setStroke(Color.WHITE);

        setAlignment(Pos.CENTER);

        getChildren().addAll(background, character);

        DropShadow drop = new DropShadow(50, Color.WHITESMOKE);
        drop.setInput(new Glow());

        setOnMouseEntered(event -> {
            background.setTranslateX(10);
            character.setTranslateX(10);
            background.setFill(Color.WHITE);
            background.setStroke(Color.DARKBLUE);
        });

        setOnMouseExited(event -> {
            background.setTranslateX(0);
            character.setTranslateX(0);
            background.setFill(Color.DARKBLUE);
            background.setStroke(Color.WHITE);
        });

        setOnMousePressed(event -> {
            background.setTranslateX(0);
            character.setTranslateX(0);
            background.setFill(Color.WHITE);
            background.setStroke(Color.DARKBLUE);
            setEffect(drop);
        });

        setOnMouseReleased(event -> setEffect(null));
    }

    public MenuButton(String name) {
        text = new Text(name);
        text.setFill(Color.WHITE);

        background = new Rectangle(250, 40);
        background.setFill(Color.DARKBLUE);
        background.setStroke(Color.WHITE);

        // Aligns the buttons text to the center of the rectangle
        setAlignment(Pos.CENTER);

        getChildren().addAll(background, text);

        // Sets effect when hovering over button
        setOnMouseEntered(event -> {
            background.setTranslateX(10);
            text.setTranslateX(10);
            background.setFill(Color.WHITE);
            text.setFill(Color.DARKBLUE);
            background.setStroke(Color.DARKBLUE);
        });

        setOnMouseExited(event -> {
            background.setTranslateX(0);
            text.setTranslateX(0);
            background.setFill(Color.DARKBLUE);
            text.setFill(Color.WHITE);
            background.setStroke(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.WHITESMOKE);
        drop.setInput(new Glow());

        // Sets effect when pressing button
        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    public Rectangle getButtonBackground() {
        return this.background;
    }
}
