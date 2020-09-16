import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
    private Text text;

    public MenuButton(String name){
        text = new Text(name);
        text.setFill(Color.WHITE);

        Rectangle background = new Rectangle(250, 40);
        background.setFill(Color.DARKBLUE);
        background.setStroke(Color.WHITE);

        setAlignment(Pos.CENTER);

        getChildren().addAll(background, text);

        setOnMouseEntered(Event -> {
            background.setTranslateX(10);
            text.setTranslateX(10);
            background.setFill(Color.WHITE);
            text.setFill(Color.DARKBLUE);
        });

        setOnMouseExited(Event -> {
            background.setTranslateX(0);
            text.setTranslateX(0);
            background.setFill(Color.DARKBLUE);
            text.setFill(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.WHITESMOKE);
        drop.setInput(new Glow());

        setOnMousePressed(mouseEvent -> setEffect(drop));
        setOnMouseReleased(mouseEvent -> setEffect(null));
    }
}
