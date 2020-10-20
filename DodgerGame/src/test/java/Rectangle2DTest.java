import Model.Rectangle2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Rectangle2DTest {

    /**
     * Tests if intersects works properly.
     *
     * @author Irja Vuorela
     */
    @Test
    public void testIntersects() {
        Rectangle2D rectangle = new Rectangle2D();
        Rectangle2D other = new Rectangle2D(500, 500);
        assertTrue(rectangle.intersects(rectangle));
        assertFalse(rectangle.intersects(other));
    }

    /**
     * Tests if size is positive after attempting to set a negative size
     *
     * @author Irja Vuorela
     */
    @Test
    public void negativeSizeTurnsPositive() {
        double size = 32;
        Rectangle2D rectangle = new Rectangle2D(0, 0, -size, -size);
        assertTrue(rectangle.getHeight() == size);
        assertTrue(rectangle.getWidth() == size);
    }
}
