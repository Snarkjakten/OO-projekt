import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @Author Isak Almeros
 */

public class MenuButton extends StackPane {
    private Text text;

    public MenuButton(String name){
        text = new Text(name);
        text.setFill(Color.WHITE);

        Rectangle background = new Rectangle(250, 40);
        background.setFill(Color.DARKBLUE);
        background.setStroke(Color.WHITE);

        // Aligns the buttons text to the center of the rectangle
        setAlignment(Pos.CENTER);

        getChildren().addAll(background, text);

        // Sets effect when hovering over button
        setOnMouseEntered(Event -> {
            background.setTranslateX(10);
            text.setTranslateX(10);
            background.setFill(Color.WHITE);
            text.setFill(Color.DARKBLUE);
            background.setStroke(Color.DARKBLUE);
        });

        setOnMouseExited(Event -> {
            background.setTranslateX(0);
            text.setTranslateX(0);
            background.setFill(Color.DARKBLUE);
            text.setFill(Color.WHITE);
            background.setStroke(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.WHITESMOKE);
        drop.setInput(new Glow());

        // Sets effect when pressing button
        setOnMousePressed(Event -> setEffect(drop));
        setOnMouseReleased(Event -> setEffect(null));
    }
}
