package Model;

import static java.lang.StrictMath.abs;

/**
 * A rectangle to be used for the game's hitBoxes.
 */

public class Rectangle2D {
    private final Point2D position;
    private double width;
    private double height;

    /**
     * A 1x1 rectangle at (0, 0)
     *
     * @author Irja Vuorela
     */
    public Rectangle2D() {
        this.position = new Point2D(0, 0);
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
        this.position = new Point2D(x, y);
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
        this.position = new Point2D(x, y);
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
        double xDistance = abs(position.getX() - other.getX());
        double yDistance = abs(position.getY() - other.getY());
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
        return ("x: " + position.getX() + ", y: " + position.getY() + ", width: " + this.width + ", height: " + this.height);
    }

    // getters and setters --------------------------------------

    /**
     * @return the position
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * @return the x-coordinate
     */
    public double getX() {
        return this.position.getX();
    }

    /**
     * @return the y-coordinate
     */
    public double getY() {
        return this.position.getY();
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

    /**
     * @param width Sets the width of this rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @param height the height of this rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

}
