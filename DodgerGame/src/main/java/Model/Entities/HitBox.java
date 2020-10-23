package Model.Entities;


public class HitBox {

    private Rectangle2D hitBox;

    /**
     * A unit HitBox
     *
     * @author Viktor & Irja
     */
    public HitBox() {
        updateHitBox(0, 0, 0, 0);
    }

    public HitBox(double xPos, double yPos, double width, double height) {
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

    /**
     * @param xPos the new xPosition for this hitBox
     * @param yPos the new yPosition for this hitbox
     * @author Tobias Engblom
     */
    public void updateHitBoxPosition(double xPos, double yPos) {
        setHitBox(new Rectangle2D(xPos, yPos, getHitBox().getWidth(), getHitBox().getHeight()));
    }

    /**
     * @param width  the new width for this hitBox
     * @param height the new height for this hitBox
     * @author Tobias Engblom
     */
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
        updateHitBox(this.getX() + xVelocity, this.getY() + yVelocity, getHitBox().getWidth(), getHitBox().getHeight());
    }

    /**
     * @return the xPosition of this hitBox
     */
    public double getX() {
        return this.hitBox.getX();
    }

    /**
     * @return the yPosition of this hitBox
     */
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

    /**
     * @return the 2D rectangle of this hitBox
     */
    public Rectangle2D getHitBox() {
        return hitBox;
    }

    /**
     * @return the hitBox width
     */
    public double getWidth() {
        return getHitBox().getWidth();
    }

    /**
     * @return the hitBox height
     */
    public double getHeight() {
        return getHitBox().getHeight();
    }
}
