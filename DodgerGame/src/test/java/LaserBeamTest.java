import Entities.LaserBeam;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LaserBeamTest {

    LaserBeam laser;

    @Before
    //@Author Olle Westerlund
    public void init() {
        laser = new LaserBeam( 0.1);
    }

    @Test
    public void testInitImages() {
        for (Image image : laser.getImages()) {
            assertTrue(image != null);
        }
    }

    @Test
    public void testGetFrame() {
        Image image = laser.getFrame(0.6);
        assertTrue(image != null);
    }

}
