package Model;

import static java.lang.StrictMath.sqrt;

/**
 * A point to be used for coordinates for a position, or as a vector with direction for velocity.
 */

public class Point2D {

    // The x- and y-values of a point
    private double x;
    private double y;

    /**
     * Creates a Point with x and y coordinates.
     *
     * @param x the point's x-value
     * @param y the point's y-value
     * @author Irja Vuorela
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a point with the coordinates of the specified point added to the coordinates of this point.
     *
     * @param other the point to be added
     * @return a point with the coordinates of the specified point added to the coordinates of this point.
     * @author Irja Vuorela
     */
    public Point2D add(Point2D other) {
        return new Point2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Returns a point with the coordinates of this point multiplied by the specified factor
     *
     * @param multiplier
     * @return a point with the coordinates of this point multiplied by the specified factor
     * @author Irja Vuorela
     */
    public Point2D multiply(double multiplier) {
        return new Point2D(this.x * multiplier, this.y * multiplier);
    }

    /**
     * Computes magnitude (length) of the vector represented by this point.
     *
     * @return the magnitude of this vector
     * @author Irja Vuorela
     */
    public double magnitude() {
        // Get magnitude using the Pythagorean theorem
        return sqrt((this.x * this.x) + (this.y * this.y));
    }

    /**
     * Normalizes a vector
     *
     * @return a vector of length 1 with the same direction
     * @author Irja Vuorela
     */
    public Point2D normalize() {
        double length = this.magnitude();
        if (!(length == 0)) {
            return new Point2D(this.x / length, this.y / length);
        } else {
            return new Point2D(0,0);
        }
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}