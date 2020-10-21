package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Olle Westerlund
 */
public class PlayingFieldGUI{
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

    public void drawBackground(double x, double y, double width, double height, int backgroundNr) {
        Image image = backgroundImages.get(backgroundNr);
        gc.drawImage(image, x, y, width, height);
    }

}
