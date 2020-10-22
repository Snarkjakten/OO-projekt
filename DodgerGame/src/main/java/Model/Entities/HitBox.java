package Model.Entities;


import Model.Rectangle2D;

public class HitBox {

    private double width;
    private double height;
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
        updateHitBox(0, 0, this.width, this.height);
    }

    public HitBox(double xPos, double yPos, double width, double height) {
        this.width = width;
        this.height = height;
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
        setHitBox(new Rectangle2D(xPos, yPos, width, height));
    }

    public void updateHitBoxPosition(double xPos, double yPos) {
        setHitBox(new Rectangle2D(xPos, yPos, this.width, this.height));
    }

    public void updateHitBoxSize(double width, double height) {
        setHitBox(new Rectangle2D(getHitBox().getX(), getHitBox().getY(), width, height));
    }

    /**
     * Updates the position of the hitBox depending on the velocity
     *
     * @param xVelocity the xVelocity for this hitBox
     * @param yVelocity the yVelocity for this hitBox
     * @author Tobias Engblom
     */
    public void updatePosition(double xVelocity, double yVelocity) {
        updateHitBox(this.getX() + xVelocity, this.getY() + yVelocity, this.width, this.height);
    }

    public double getX() {
        return this.hitBox.getX();
    }

    public double getY() {
        return this.hitBox.getY();
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
