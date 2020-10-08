package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class HealthBarGUI {
    private GraphicsContext gc;
    private Image background;
    private Image foreground;
    private Image border;

    public HealthBarGUI(GraphicsContext gc) {
        this.gc = gc;
        initImages();
    }

    private void initImages() {
        this.background = addBackgroundToHpBar();
        this.foreground = addForegroundToHpBar();
        this.border = addBorderToHpBar();
    }

    private Image addBackgroundToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpBackground.png");
        background = new Image(inputStream);
        return background;
    }

    private Image addForegroundToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpForeground.png");
        foreground = new Image(inputStream);
        return foreground;
    }

    private Image addBorderToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpBorder.png");
        border = new Image(inputStream);
        return border;
    }

    public void drawHealthBar(double remainingHp) {
        gc.drawImage(background, 0, 0, 200, 40);
        gc.drawImage(foreground, 0, 0, remainingHp, 40);
        gc.drawImage(border, 0, 0, 200, 40);
    }
}
