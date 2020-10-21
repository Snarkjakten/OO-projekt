package View;

import Interfaces.ISpaceshipObserver;
import Model.Entities.Player.Spaceship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class HealthBarGUI implements ISpaceshipObserver {
    private final GraphicsContext gc;
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
        assert inputStream != null;
        background = new Image(inputStream);
        return background;
    }

    private Image addForegroundToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpForeground.png");
        assert inputStream != null;
        foreground = new Image(inputStream);
        return foreground;
    }

    private Image addBorderToHpBar() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hpBorder.png");
        assert inputStream != null;
        border = new Image(inputStream);
        return border;
    }

    /**
     *
     * @param remainingHp the value of health point that remains
     * @param maxHealth the maximum amount of health point
     * @author Olle Westerlund
     */
    private void drawHealthBar(double remainingHp, double maxHealth) {
        gc.drawImage(background, 0, 0, maxHealth, 40);
        gc.drawImage(foreground, 0, 0, remainingHp, 40);
        gc.drawImage(border, 0, 0, maxHealth, 40);
    }

    @Override
    public void actOnEvent(Spaceship spaceship) {
        drawHealthBar(spaceship.getHp(), spaceship.getMaxHp());
    }
}
