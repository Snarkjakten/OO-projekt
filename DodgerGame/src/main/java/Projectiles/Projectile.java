package Projectiles;

import Move.*;
import java.util.Random;

/**
 * @Author Olle Westerlund
 */

public abstract class Projectile implements IMovable {
    private int diameter;
    private double speed;
    private double xPos;
    private double yPos;
    private Direction direction;

    public Projectile(int diameter, double speed, double xPos, double yPos) {
        this.diameter = diameter;
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = randomDirection();
    }

    public static Direction randomDirection() {
        Random randDirection = new Random();
        int number = randDirection.nextInt(8);
        switch (number) {
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.EAST;
            case 2:
                return Direction.SOUTH;
            case 3:
                return Direction.WEST;
            case 4:
                return Direction.NORTHEAST;
            case 5:
                return Direction.SOUTHEAST;
            case 6:
                return Direction.SOUTHWEST;
            case 7:
                return Direction.NORTHWEST;
            default:
                System.out.println("Wrong direction");
                break;
        }
        return Direction.WEST;
    }

    public void moveNorth() {

    }

    public void moveSouth() {

    }

    public void moveWest() {

    }

    public void moveEast() {

    }

    public void moveNorthWest() {

    }

    public void moveNorthEast() {

    }

    public void moveSouthWest() {

    }

    public void moveSouthEast() {

    }

    public int getDiameter() {
        return diameter;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
