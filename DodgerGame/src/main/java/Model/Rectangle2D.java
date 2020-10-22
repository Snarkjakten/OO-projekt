package Model;

import static java.lang.StrictMath.abs;

/**
 * A rectangle to be used for the game's hitboxes.
 */

public class Rectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * A 1x1 rectangle at (0, 0)
     *
     * @author Irja Vuorela
     */
    public Rectangle2D() {
        this.x = 0.0;
        this.y = 0.0;
        this.width = 1.0;
        this.height = 1.0;
    }

    /**
     * A 1x1 rectangle at (x, y)
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @author Irja Vuorela
     */
    public Rectangle2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 1.0;
        this.height = 1.0;
    }

    /**
     * A rectangle at (x, y) with custom size.
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @author Irja Vuorela
     */
    public Rectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = abs(width);
        this.height = abs(height);
    }

    /**
     * Checks if your rectangle intersects with another rectangle.
     *
     * @param other an other rectangle
     * @return true if the other rectangle intersects with yours, false if not
     * @author Irja Vuorela
     */
    public boolean intersects(Rectangle2D other) {
        // the distances between this rectangle's and the other rectangle's x- and y-values.
        double xDistance = abs(this.x - other.getX());
        double yDistance = abs(this.y - other.getY());
        // the shortest possible distance between the two rectangles' centres without overlap
        double shortestY = (this.height + other.getHeight()) / 2.0;
        double shortestX = (this.width + other.getWidth() / 2.0);
        // they intersect if both xDistance and yDistance are shorter than the shortest possible distance
        return ((xDistance < shortestX) && (yDistance < shortestY));
    }

    /**
     * @return string
     * @author Irja Vuorela
     */
    @Override
    public String toString() {
        return ("x: " + this.x + ", y: " + this.y + ", width: " + this.width + ", height: " + this.height);
    }

    // getters and setters --------------------------------------

    /**
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @return the width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

}
