import Model.Entities.Rectangle2D;
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
        Rectangle2D rectangle = new Rectangle2D(10, 10);
        Rectangle2D other = new Rectangle2D(500, 500);
        Rectangle2D rectangle2 = new Rectangle2D(10, 10);
        assertTrue(rectangle.intersects(rectangle));
        assertFalse(rectangle.intersects(other));
        assertTrue(rectangle.intersects(rectangle2));
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
        assertEquals(rectangle.getHeight(), size, 0.0);
        assertEquals(rectangle.getWidth(), size, 0.0);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testDefaultConstructor() {
        Rectangle2D rectangle = new Rectangle2D();
        assertTrue(rectangle.getX() == 0 && rectangle.getY() == 0);
        assertTrue(rectangle.getWidth() == 1 && rectangle.getHeight() == 1);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void rectangleToString() {
        Rectangle2D rectangle = new Rectangle2D(20, 20, 20, 20);
        assertEquals("x: 20.0, y: 20.0, width: 20.0, height: 20.0", rectangle.toString());
    }
}
