package Model.Entities;


import Model.Point2D;
import Model.Rectangle2D;

public class HitBox {

    private double width;
    private double height;
    // Position (x, y)
    private Point2D position;
    private Rectangle2D hitBox;

    public HitBox(double xPos, double yPos, double width, double height) {
        this.width = width;
        this.height = height;
        setPosition(new Point2D(xPos, yPos));
        updateHitBox(xPos, yPos, width, height);
    }

    /**
     * Creates a new Rectangle2D with the new values
     *
     * @param xPos   new xPosition
     * @param yPos   new yPosition
     * @param width  new Width
     * @param height new Height
     * @author Tobias Engblom
     */
    public void updateHitBox(double xPos, double yPos, double width, double height) {
        setHitBox(new Rectangle2D(xPos, yPos, width * 0.6, height * 0.6));
        setPosition(new Point2D(xPos, yPos));
    }

    /**
     * Updates the position of the hitBox depending on the velocity
     *
     * @param xVelocity the xVelocity for this hitBox
     * @param yVelocity the yVelocity for this hitBox
     * @author Tobias Engblom
     */
    public void updatePosition(double xVelocity, double yVelocity) {
        this.position = position.add(xVelocity, yVelocity);
        updateHitBox(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    /**
     * @param hitBox the new 2D rectangle for this hitBox
     * @author Tobias Engblom
     */
    private void setHitBox(Rectangle2D hitBox) {
        this.hitBox = hitBox;
    }

    /**
     * @return the Rectangle2D hitBox of this hitBox
     * @author Tobias Engblom
     */
    public Rectangle2D getHitBox() {
        return hitBox;
    }

    /**
     * @return the position of this hitBox
     * @author Tobias Engblom
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Setter for this hitBox position
     *
     * @param position the new 2D point for this hitBox
     * @author Tobias Engblom
     */
    private void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * The hitBox width is 0.6 times the width of the game object
     *
     * @param width the new width
     * @author Tobias Engblom
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * The hitBox height is 0.6 times the height of the game object
     *
     * @param height the new height
     * @author Tobias Engblom
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the width of this hitBox
     * @author Tobias Engblom
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of this hitBox
     * @author Tobias Engblom
     */
    public double getHeight() {
        return height;
    }
}
