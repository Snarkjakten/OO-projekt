import Model.Point2D;

import org.junit.Test;
import static org.junit.Assert.*;

public class Point2DTest {


    /**
     * Tests if the add methods add correctly.
     *
     * @author Irja Vuorela
     */
    @Test
    public void addedPoint() {
        double x = 1;
        double y = 1;
        Point2D point = new Point2D(0, 0);
        Point2D other = new Point2D(x, y);
        Point2D add1 = point.add(other);
        Point2D add2 = point.add(x, y);
        assertTrue(add1.getX() == (point.getX() + other.getX()) && add1.getY() == (point.getY()) + other.getY());
        assertTrue(add2.getX() == (point.getX() + other.getX()) && add2.getY() == (point.getY()) + other.getY());
    }

    /**
     * Tests if multiply multiplies correctly.
     *
     * @author Irja Vuorela
     */
    @Test
    public void multipliedFactorToPoint() {
        double x = 2.0;
        double y = 3.0;
        double factor = 5.0;
        Point2D point = new Point2D(x, y);
        point = point.multiply(factor);
        assertEquals(x * factor, point.getX(), 0.0000001);
        assertEquals(y * factor, point.getY(), 0.0000001);
    }

    /**
     * Tests if magnitude returns the correct magnitude.
     *
     * @author Irja Vuorela
     */
    @Test
    public void correctMagnitude() {
        Point2D verticalVector = new Point2D(0, 10);
        Point2D horizontalVector = new Point2D(20, 0);
        Point2D diagonalVector = new Point2D(3, 4);
        // Compare magnitudes to the expected values
        assertEquals(verticalVector.magnitude(), 10, 0.0000001);
        assertEquals(horizontalVector.magnitude(), 20, 0.0000001);
        assertEquals(diagonalVector.magnitude(), 5, 0.0000001);
    }

    /**
     * Tests if normalize returns the correct magnitude.
     *
     * @author Irja Vuorela
     */
    @Test
    public void correctNormalize() {
        Point2D point = new Point2D(3, 4);
        // check that magnitude wasn't 1 from the beginning
        assertNotEquals(point.magnitude(), 1, 0.0000001);
        point = point.normalize();
        // compare magnitude to the expected value
        assertEquals(point.magnitude(), 1, 0.0000001);
    }


}
