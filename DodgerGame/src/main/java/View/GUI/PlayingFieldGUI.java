package View.GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Olle Westerlund
 */
public class PlayingFieldGUI {
    private GraphicsContext gc;
    private ArrayList<Image> backgroundImages;

    public PlayingFieldGUI(GraphicsContext gc) {
        this.gc = gc;
        this.backgroundImages = new ArrayList<>();
        initImages();
    }

    private void initImages() {
        backgroundImages.add(addBackgroundImage());
    }

    private Image addBackgroundImage() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
        Image image = new Image(inputStream);
        return image;
    }

    /**
     * Draws the background image
     *
     * @param x            the starting x position for drawing the image
     * @param y            the starting y position for drawing the image
     * @param width        the width for the image
     * @param height       the height for the iamge
     * @param backgroundNr sets what image to use
     * @author Olle Westerlund
     */
    public void drawBackground(double x, double y, double width, double height, int backgroundNr) {
        if (backgroundNr > backgroundImages.size() - 1) {
            throw new IllegalArgumentException("Background does not exist");
        } else {
            Image image = backgroundImages.get(backgroundNr);
            gc.drawImage(image, x, y, width, height);
        }
    }

}
