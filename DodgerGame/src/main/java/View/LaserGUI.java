package View;

import Interfaces.IGameObjectObserver;
import Interfaces.ITimeObserver;
import Model.GameWorld;
import Model.Entities.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

/**
 * @author Olle Westerlund
 */
public class LaserGUI implements ITimeObserver { // , IGameObjectObserver
    private double animationTime;
    private GraphicsContext gc;
    private Image[] frames;
    private final double duration = 0.1;
    private boolean isVertical = true;
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

    public void setIsVertical(boolean isVertical) {
        this.isVertical = isVertical;
        this.frames = new Image[8];
        initImages();
    }

//    public void drawLaser(LaserBeam laserBeam) {
//        this.isVertical = laserBeam.isVertical();
//        gc.drawImage(this.getFrame(animationTime), laserBeam.getPosition().getX(), laserBeam.position.getY());
//    }

    public Image getImage() {
//        initImages();
        return getFrame(animationTime);
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
            image = new Image(inputStream, 256, playingFieldHeight + 100, false, false);
        } else {
            url = "LaserBeam/laser0" + imageNumber + "H.png";
            inputStream = getClass().getClassLoader().getResourceAsStream(url);
            image = new Image(inputStream, playingFieldWidth + 100, 256, false, false);
        }
        return image;
    }

    @Override
    public void actOnEvent(long time, double deltaTime) {
        this.animationTime = deltaTime;
    }


    /**
     * @param x
     * @param y
     * @param c
     * @param height
     * @param width
     * @author Irja Vuorela & Viktor Sundberg
     */
    /*
    @Override
    public void actOnEvent(double x, double y, Class c, double height, double width) {
        drawLaser(duration, x, y);
    }
    */
}
