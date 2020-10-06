package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @Author Olle Westerlund
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
     * @Author Olle Westerlund
     * The method decides which image to show.
     * @param time is used to calculate which index is used.
     * @return The image that is going to be displayed at the current time.
     */
    private Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * @Author Olle Westerlund
     * Loops through the array and sets an image on every position.
     */
    private void initImages() {
        for (int i = 0; i < 8; i++) {
            frames[i] = setImage(i);
        }
    }

    /**
     * @Author Olle Westerlund
     * Sets the corresponding image depending on isHorizontal and the number passed to the method.
     * @param number Which picture number the animation needs.
     * @return The correct image and size of the image.
     */
    private Image setImage(int number) {
        InputStream inputStream;
        Image image;
        switch (number) {
            case 0:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser01H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 1:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser02H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 2:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser03H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 3:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser04H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 4:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser05H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 5:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser06H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 6:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser07H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            case 7:
                if (isVertical) {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08V.png");
                    image = new Image(inputStream, 256, 700, false, false);
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream("LaserBeam/laser08H.png");
                    image = new Image(inputStream, 900, 256, false, false);
                }
                return image;
            default:
                System.out.println("Failed to set image to laser");
                return null;
        }
    }

    public Image[] getImages() {
        return frames;
    }

}
