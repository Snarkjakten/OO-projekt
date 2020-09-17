package Entities.Projectiles;

import Movement.*;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * @Author Olle Westerlund
 */

public abstract class Projectile extends AbstractMovable {
    private double speed;               // Speed of the projectile.
    private double horizontal = 0.0;    // positive value: right, negative value: left
    private double vertical = 0.0;      // positive value: up, negative value: down
    private Direction direction;        // The direction of the projectile.

    public Projectile(double speed) {
        this.speed = speed;
        this.direction = randomDirection();
        randomPosition(direction);
    }

    //TODO: Add different constructor for running scripted versions.
    public Projectile(double speed, String scriptVersion) {

    }

    /**
     * The method sets a random starting position for the projectile and changes
     * the direction if needed to a specific direction.
     * @param direction The current direction of the projectile.
     */
    private void randomPosition(Direction direction) {
        Random randPos = new Random();
        int side = randPos.nextInt(4);
        switch (side) {
            case 0: //Bottom side of the screen
                this.horizontal = randPos.nextDouble() * 1200; // use this.position
                this.vertical = 0.0;
                if (direction.equals(Direction.SOUTH) || direction.equals(Direction.SOUTHEAST) ||
                    direction.equals(Direction.SOUTHWEST)) {
                    this.direction = specificDirection("NW");
                }
                updatePosition();
                break;
            case 1: //Right side of the screen
                this.horizontal = 1200;
                this.vertical = randPos.nextDouble() * 800;
                if (direction.equals(Direction.EAST) || direction.equals(Direction.SOUTHEAST) ||
                    direction.equals(Direction.NORTHEAST)) {
                    this.direction = specificDirection("SW");
                }
                updatePosition();
                break;
            case 2: //Top side of the screen
                this.horizontal = 800.0;
                this.vertical = randPos.nextDouble() * 1200;
                if (direction.equals(Direction.NORTH) || direction.equals(Direction.NORTHEAST) ||
                    direction.equals(Direction.NORTHWEST)) {
                    this.direction = specificDirection("SE");
                }
                updatePosition();
                break;
            case 3: //Left side of the screen
                this.horizontal = 0.0;
                this.vertical = randPos.nextDouble() * 800;
                if (direction.equals(Direction.WEST) || direction.equals(Direction.SOUTHWEST) ||
                    direction.equals(Direction.NORTHWEST)) {
                    this.direction = specificDirection("NE");
                }
                updatePosition();
                break;
            default:
                this.horizontal = 0.0;
                this.vertical = 400;
                this.direction = specificDirection("E");
                updatePosition();
                break;
        }
    }

    /**
     * The method sets a specific direction from the input.
     * @param direction The desired direction for the projectile.
     * @return The chosen direction.
     */
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
                System.out.println("Wrong input, setting west as default");
                return Direction.WEST;
        }
    }

    /**
     * The method creates a random direction.
     * @return A random direction.
     */
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
                System.out.println("Random out of range.");
                break;
        }
        return Direction.WEST;
    }

    /**
     * Changing the position of the projectile depending on the direction and the speed
     * of said projectile.
     */
    @Override
    public void move() {
        switch (this.direction) {
            case NORTH:
                setVertical(getVertical() + speed);
                break;
            case WEST:
                setHorizontal(getHorizontal() - speed);
                break;
            case SOUTH:
                setVertical(getVertical() - speed);
                break;
            case EAST:
                setHorizontal(getHorizontal() + speed);
                break;
            case NORTHEAST:
                setHorizontal(getHorizontal() + speed);
                setVertical(getVertical() + speed);
                break;
            case NORTHWEST:
                setVertical(getVertical() + speed);
                setHorizontal(getVertical() - speed);
                break;
            case SOUTHEAST:
                setVertical(getVertical() - speed);
                setHorizontal(getHorizontal() + speed);
                break;
            case SOUTHWEST:
                setHorizontal(getHorizontal() - speed);
                setVertical(getVertical() - speed);
                break;
            default:
                System.out.println("Projectile not moving");
                break;
        }
        updateVelocity();
        updatePosition();

         System.out.println("Projectile moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // Update velocity
    // @Author Irja Vuorela
    public void updateVelocity() {
        // Normalize velocity
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        // Multiply with speed
        this.velocity = new Point2D(horizontal, vertical);
//        this.velocity = new Point2D(horizontal * speed, vertical * speed);
    }

    public double getSpeed() {
        return speed;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    protected void setVertical(double vertical) {
        this.vertical = vertical;
    }
}
