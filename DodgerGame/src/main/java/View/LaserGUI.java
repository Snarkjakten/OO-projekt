package View;

import Interfaces.IGameObjectObserver;
import Model.Entities.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

/**
 * @author Olle Westerlund
 */
public class LaserGUI {
    private GraphicsContext gc;
    private Image[] frames;
    private boolean isVertical;
    private double duration;

    public LaserGUI(GraphicsContext gc, double duration, boolean isVertical) {
        this.gc = gc;
        this.duration = duration;
        this.isVertical = isVertical;
        this.frames = new Image[8];
        initImages();
    }

    public void drawLaser(double animationTime, double xPos, double yPos) {
        gc.drawImage(this.getFrame(animationTime), xPos, yPos);
    }

    /**
     * @param time is used to calculate which index is used.
     * @return The image that is going to be displayed at the current time.
     * @author Olle Westerlund
     * The method decides which image to show.
     */
    private Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * @author Olle Westerlund
     * Loops through the array and sets an image on every position.
     */
    private void initImages() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * @param number Which picture number the animation needs.
     * @return The correct image and size of the image.
     * @author Olle Westerlund
     * Sets the corresponding image depending on isHorizontal and the number passed to the method.
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        String url;
        int imageNumber = number + 1;
        if (isVertical) {
            url = "LaserBeam/laser0" + imageNumber + "V.png";
            inputStream = getClass().getClassLoader().getResourceAsStream(url);
            image = new Image(inputStream, 256, 700, false, false);
        } else {
            url = "LaserBeam/laser0" + imageNumber + "H.png";
            inputStream = getClass().getClassLoader().getResourceAsStream(url);
            image = new Image(inputStream, 900, 256, false, false);
        }
        return image;
    }
}
