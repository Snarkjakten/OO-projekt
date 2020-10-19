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
     * Adds the values from another point.
     *
     * @param other the point to be added
     * @return a new point with values from another point added
     * @author Irja Vuorela
     */
    public Point2D add(Point2D other) {
        return new Point2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Scale a point with a multiplier.
     *
     * @param multiplier
     * @return a point scaled with a multiplier
     * @author Irja Vuorela
     */
    public Point2D scale(double multiplier) {
        return new Point2D(this.x * multiplier, this.y * multiplier);
    }

    /**
     * Calculates the magnitude (length) of a vector
     *
     * @return magnitude
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
        double length = magnitude();
        return new Point2D(this.x / length, this.y / length);
        // return new Point2D().scale(1 / length);
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}