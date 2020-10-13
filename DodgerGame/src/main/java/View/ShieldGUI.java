package View;

import Game.Entities.Player.Player;
import Game.Entities.Player.Spaceship;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Olle Westerlund
 */
public class ShieldGUI {
    private GraphicsContext gc;
    private Image[] frames;
    private double duration;

    public ShieldGUI(GraphicsContext gc) {
        this.gc = gc;
        this.duration = 0.1;
        this.frames = new Image[20];
        initImages();

    }

    /**
     * @author Olle Westerlund
     * Sets a image to all positions in the list.
     */
    private void initImages() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * @author Olle Westerlund
     * Loads a specific image and returns it.
     * @param number The number for the picture that the is needed.
     * @return the loaded image.
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        String url = "shield/shield" + number + ".png";
        inputStream = getClass().getClassLoader().getResourceAsStream(url);
        image = new Image(inputStream);
        return image;
    }

    /**
     * @author Olle Westerlund
     * Dicides which image to show depending on the time, number of images and
     * the duration each images is shown.
     * @param time The current animation time.
     * @return The image that is going to be displayed at the current time.
     */
    private Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * @author Olle Westerlund
     * Draws the image on the players position if the player has a shield.
     * @param player The current player.
     * @param animationTime The current time for the animation
     */
    public void drawImage(Player player, double animationTime) {
        if (player.getNrOfShields() > 0) {
            Image image = getFrame(animationTime);
            for (Spaceship ship : player.getSpaceships()) {
                double xPos = ship.position.getX() - 7;
                double yPos = ship.position.getY() - 7;
                double height = ship.getHeight() * 1.25;
                double width = ship.getWidth() * 1.25;
                gc.drawImage(image, xPos, yPos, height, width);
            }
        }
    }
}
