package Projectiles;

import Move.*;
import java.util.Random;

/**
 * @Author Olle Westerlund
 */

public abstract class Projectile implements IMovable {
    private double speed;
    private double xPos = 0.0;
    private double yPos = 0.0;
    private Direction direction;

    public Projectile(double speed) {
        this.speed = speed;
        this.direction = randomDirection();
    }

    //TODO: Add different scripted versions.
    public Projectile(double speed, String scriptVersion) {

    }

    private void randomPosition(Direction direction) {
        Random randPos = new Random();
        int side = randPos.nextInt(4);
        switch (side) {
            case 0: //Bottom side of the screen
                this.xPos = randPos.nextDouble() * 1200;
                this.yPos = 0.0;
                if (direction.equals(Direction.SOUTH) || direction.equals(Direction.SOUTHEAST) ||
                    direction.equals(Direction.SOUTHWEST)) {
                    this.direction = specificDirection("NW");
                }
                break;
            case 1: //Right side of the screen
                this.xPos = 1200;
                this.yPos = randPos.nextDouble() * 800;
                if (direction.equals(Direction.EAST) || direction.equals(Direction.SOUTHEAST) ||
                    direction.equals(Direction.NORTHEAST)) {
                    this.direction = specificDirection("SW");
                }
                break;
            case 2: //Top side of the screen
                this.yPos = 800.0;
                this.xPos = randPos.nextDouble() * 1200;
                if (direction.equals(Direction.NORTH) || direction.equals(Direction.NORTHEAST) ||
                    direction.equals(Direction.NORTHWEST)) {
                    this.direction = specificDirection("SE");
                }
                break;
            case 3: //Left side of the screen
                this.xPos = 0.0;
                this.yPos = randPos.nextDouble() * 800;
                if (direction.equals(Direction.WEST) || direction.equals(Direction.SOUTHWEST) ||
                    direction.equals(Direction.NORTHWEST)) {
                    this.direction = specificDirection("NE");
                }
        }
    }

    private static Direction specificDirection(String direction) {
        switch (direction) {
            case "N":
                return Direction.NORTH;
            case "NW":
                return Direction.NORTHWEST;
            case "NE":
                return Direction.NORTHEAST;
            case "S":
                return Direction.SOUTH;
            case "SW":
                return Direction.SOUTHWEST;
            case "SE":
                return Direction.SOUTHEAST;
            case "W":
                return Direction.WEST;
            case "E":
                return Direction.EAST;
            default:
                System.out.println("Wrong input");
                return Direction.WEST;
        }
    }

    private static Direction randomDirection() {
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
                System.out.println("Wrong number from random.");
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

    public double getSpeed() {
        return speed;
    }

    public double getxPos() {
        return xPos;
    }


    public double getyPos() {
        return yPos;
    }


    public Direction getDirection() {
        return direction;
    }


}
