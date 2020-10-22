package Model.Entities;


import Model.Point2D;
import Model.Rectangle2D;

public class HitBox {

    private double width;
    private double height;
    // Position (x, y)
    //private Point2D position;
    private Rectangle2D hitBox;

    /**
     * A unit HitBox
     *
     * @author Viktor & Irja
     */
    public HitBox() {
        //this.position = new Point2D(0, 0);
        this.width = 1;
        this.height = 1;
        setHitBox(0, 0, this.width, this.height);
    }

    public HitBox(double xPos, double yPos, double width, double height) {
        this.width = width;
        this.height = height;
        // this.position = new Point2D(xPos, yPos);
        setHitBox(xPos, yPos, this.width, this.height);
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
        setHitBox(new Rectangle2D(xPos, yPos, width, height));
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
        updateHitBox(this.getXPos() + xVelocity, this.getYPos() + yVelocity, this.width, this.height);
    }

    public double getXPos() {
        return this.hitBox.getX();
    }

    public double getYPos() {
        return this.hitBox.getY();
    }


    public void setHitBox(double xPos, double yPos, double width, double height) {
        this.hitBox = new Rectangle2D(xPos, yPos, width, height);
    }

    /**
     * @param hitBox the new 2D rectangle for this hitBox
     * @author Tobias Engblom
     */
    private void setHitBox(Rectangle2D hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }


    /**
     * Setter for this hitBox position
     *
     * @param xPos the x-coordinate
     * @param yPos the y-coordinate
     * @author Tobias Engblom
     */
    public void setPosition(double xPos, double yPos) {
        this.hitBox = new Rectangle2D(xPos, yPos, this.width, this.height);
    }

    public Point2D getPosition(){
        return new Point2D(this.hitBox.getX(), this.hitBox.getY());
    }

    /**
     * Setter for this hitBox position
     *
     * @param position the new 2D point for this hitBox
     * @author Tobias Engblom
     */
    private void setPosition(Point2D position) {
        this.hitBox = new Rectangle2D(position.getX(), position.getY(), this.width, this.height);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
