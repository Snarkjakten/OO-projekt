package View;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// @Author Tobias Engblom
public class ButtonMenu extends Parent {
    private final HBox hBox;
    private final VBox vBox;

    public ButtonMenu(int space) {
        this.hBox = new HBox(space);
        this.vBox = new VBox(space);
        getChildren().addAll(hBox, vBox);
    }

    public HBox gethBox() {
        return hBox;
    }

    public VBox getvBox() {
        return vBox;
    }
}
