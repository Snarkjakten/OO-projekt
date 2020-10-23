package View.GUI;

import Model.GameWorld;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Olle Westerlund
 */
public class LaserGUI {
    private Image[] frames;
    private final double duration = 0.1;
    private boolean isVertical;
    private final double playingFieldWidth = GameWorld.getInstance().getPlayingFieldWidth();
    private final double playingFieldHeight = GameWorld.getInstance().getPlayingFieldHeight();
    private static LaserGUI instance = null;

    private LaserGUI() {
        this.frames = new Image[8];
        initImages();
    }

    public static LaserGUI getInstance() {
        if (instance == null) {
            instance = new LaserGUI();
        }
        return instance;
    }

    /**
     * Sets if the laser beam is vertical or horizontal and
     * then updates the list of images
     * @param isVertical boolean for if the laser beam is vertical or horizontal
     * @author Olle Westerlund
     */
    public void setIsVertical(boolean isVertical) {
        this.isVertical = isVertical;
        this.frames = new Image[8];
        initImages();
    }

    /**
     * The method decides which image to show.
     * @param time is used to calculate which index is used.
     * @return The image that is going to be displayed at the current time.
     * @author Olle Westerlund
     */
    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * Loops through the array and sets an image on every position.
     * @author Olle Westerlund
     */
    private void initImages() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * Sets the corresponding image depending on isHorizontal and the number passed to the method.
     * @param number Which picture number the animation needs.
     * @return The correct image and size of the image.
     * @author Olle Westerlund
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        String url;
        int imageNumber = number + 1;
        if (isVertical) {
            url = "LaserBeam/laser0" + imageNumber + "V.png";
            inputStream = getClass().getClassLoader().getResourceAsStream(url);
            image = new Image(inputStream, 256, playingFieldHeight + 100, false, false);
        } else {
            url = "LaserBeam/laser0" + imageNumber + "H.png";
            inputStream = getClass().getClassLoader().getResourceAsStream(url);
            image = new Image(inputStream, playingFieldWidth + 100, 256, false, false);
        }
        return image;
    }
}
