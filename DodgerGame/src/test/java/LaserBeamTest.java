import Entities.LaserBeam;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LaserBeamTest {

    LaserBeam laserHorizontal;
    LaserBeam laserVertical;

    @Before
    public void init() {
        laserHorizontal = new LaserBeam(400, 0.1, true);
        laserVertical = new LaserBeam(300,0.1, false);
    }

    @Test
    public void testInitImagesHorizontal() {
        for (Image image : laserHorizontal.getImages()) {
            assertTrue(image != null);
        }
    }

    @Test
    public void testInitImagesVertical() {
        for (Image image : laserVertical.getImages()) {
            assertTrue(image != null);
        }
    }

    @Test
    public void testHorizontalPosition() {
        double xPos = laserHorizontal.getHorizontal();
        double yPos = laserHorizontal.getVertical();
        assertTrue((xPos == 400 - (256/2)) && yPos == -50);
    }

    @Test
    public void testVerticalPosition() {
        double xPos = laserVertical.getHorizontal();
        double yPos = laserVertical.getVertical();
        assertTrue((xPos == -50) && yPos == (300 - 256/2));
    }


}
