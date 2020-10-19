import Model.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    /**
     * Tests if intersects works properly.
     *
     * @author Irja Vuorela
     */
    @Test
    public void testIntersects() {
        Rectangle rectangle = new Rectangle();
        Rectangle other = new Rectangle(500, 500);
        assertTrue(rectangle.intersects(rectangle)); // Same location
        assertFalse(rectangle.intersects(other)); // Far from each other
    }

    /**
     * Tests if size is positive after attempting to set a negative size
     *
     * @author Irja Vuorela
     */
    @Test
    public void negativeSizeTurnsPositive() {
        double size = 32;
        Rectangle rectangle = new Rectangle(0, 0, -size, -size);
        assertTrue(rectangle.getHeight() == size);
        assertTrue(rectangle.getWidth() == size);
    }
}
