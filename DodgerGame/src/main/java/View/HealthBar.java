package View;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.InputStream;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class HealthBar extends Pane {

    public Image background;
    public Image foreground;
    public Image border;
    //Rectangle hpBackground;
    //Rectangle hpBar;

    public Image addBackgroundToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpBackground.png");
        background = new Image(inputStream);
        return background;
    }

    public Image addForegroundToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpForeground.png");
        foreground = new Image(inputStream);
        return foreground;
    }

    public Image addBorderToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpBorder.png");
        border = new Image(inputStream);
        return border;
    }
}
