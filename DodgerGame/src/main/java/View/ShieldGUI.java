package View;

import Model.Entities.HitBox;
import Interfaces.ISpaceshipObserver;
import Model.Entities.Player.Spaceship;
import Interfaces.ITimeObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Olle Westerlund
 */
public class ShieldGUI implements ISpaceshipObserver, ITimeObserver {
    private final GraphicsContext gc;
    private final Image[] frames;
    private final double duration;
    private double animationTime;

    public ShieldGUI(GraphicsContext gc) {
        this.gc = gc;
        this.duration = 0.1;
        this.frames = new Image[20];
        initImages();

    }

    /**
     * Sets a image to all positions in the list.
     *
     * @author Olle Westerlund
     */
    private void initImages() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * Loads a specific image and returns it.
     *
     * @param number The number for the picture that the is needed.
     * @return the loaded image.
     * @author Olle Westerlund
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        String url = "shield/shield" + number + ".png";
        inputStream = getClass().getClassLoader().getResourceAsStream(url);
        assert inputStream != null;
        image = new Image(inputStream);
        return image;
    }

    /**
     * Dicides which image to show depending on the time, number of images and
     * the duration each images is shown.
     *
     * @param time The current animation time.
     * @return The image that is going to be displayed at the current time.
     * @author Olle Westerlund
     */
    private Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * Draws the image on the players position if the player has a shield.
     *
     * @param spaceship The current spaceship.
     * @author Olle Westerlund
     */
    private void drawImage(Spaceship spaceship) {
        if (spaceship.getNrOfShields() > 0) {
            Image image = getFrame(animationTime);
            for (HitBox hitBox : spaceship.getHitBoxes()) {
                double xPos = hitBox.getX() - 37;
                double yPos = hitBox.getY() - 37;
                double height = hitBox.getHeight() * 2.25;
                double width = hitBox.getWidth() * 2.25;
                gc.drawImage(image, xPos, yPos, height, width);
            }
        }
    }

    @Override
    public void actOnEvent(long time, double deltaTime) {
        this.animationTime = deltaTime;
    }

    @Override
    public void actOnEvent(Spaceship spaceship) {
        drawImage(spaceship);
    }
}
