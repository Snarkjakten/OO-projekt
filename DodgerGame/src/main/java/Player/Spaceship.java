package Player;

public class Spaceship {
    private double currentSpeed;
    private double xPosition;
    private double yPosition;

    public Spaceship() {
        this.currentSpeed = 0;
        this.xPosition = 0;
        this.yPosition = 0;
    }

    protected double getxPosition() {
        return xPosition;
    }

    protected void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    protected double getyPosition() {
        return yPosition;
    }

    protected void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
