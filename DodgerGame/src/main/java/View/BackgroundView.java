package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * @author Olle Westerlund
 */
public class BackgroundView {
    private GraphicsContext gc;
    private Image[] images;

    public BackgroundView(GraphicsContext gc) {
        this.gc = gc;
        this.images = new Image[1];
        initImages();
    }

    private void initImages() {
        for (int i = 0; i < images.length; i++) {
            images[i] = addImage();
        }
    }

    private Image addImage() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("space.jpg");
        Image image = new Image(inputStream);
        return image;
    }

    public void drawBackground(double x, double y, double height, double width, int backgroundNr) {
        Image image = images[backgroundNr];
        gc.drawImage(image, x, y, width, height);
    }

}
