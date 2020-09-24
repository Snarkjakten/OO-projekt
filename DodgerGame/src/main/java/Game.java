import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Entities.Player.SpaceshipGUI;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance = null;
    private Spaceship spaceship = SpaceshipFactory.createSpaceship(true);
    private SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 368, 268);
    private Spaceship wrapAroundSpaceship = SpaceshipFactory.createSpaceship(false);
    private SpaceshipGUI wrapAroundSpaceshipGUI = new SpaceshipGUI(wrapAroundSpaceship, 900, 900);
    private List<Spaceship> spaceships = new ArrayList<>();

    private Game() {
    }

    //@Author Tobias Engblom
    protected static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.initSpaceships();
        }
        return instance;
    }

    //@Author Tobias Engblom
    private void initSpaceships() {
        spaceships.add(spaceship);
        spaceships.add(wrapAroundSpaceship);
    }

    //@Author Tobias Engblom
    protected SpaceshipGUI getSpaceshipGUI() {
        return spaceshipGUI;
    }

    //@Author Tobias Engblom
    protected SpaceshipGUI getWrapAroundSpaceshipGUI() {
        return wrapAroundSpaceshipGUI;
    }

    //@Author Tobias Engblom
    protected List<Spaceship> getSpaceships() {
        return spaceships;
    }

    //@Author Tobias Engblom
    protected void wrapAround() {
        if (spaceship.isActive() && wrapAroundSpaceship.isActive()) {
            //TODO What happens when both ships are active and crosses the "wall"?
        } else if (spaceship.isActive()) {
            activateWrapAround(spaceshipGUI, wrapAroundSpaceshipGUI);
        } else if (wrapAroundSpaceship.isActive()) {
            activateWrapAround(wrapAroundSpaceshipGUI, spaceshipGUI);
        }
        if (checkInactive(spaceshipGUI))
            spaceship.setActive(false);
        else if (checkInactive(wrapAroundSpaceshipGUI))
            wrapAroundSpaceship.setActive(false);
    }

    //@Author Tobias Engblom
    //Inactivates spaceship if it's outside the map
    private boolean checkInactive(SpaceshipGUI spaceshipGUI) {
        return spaceshipGUI.getXPosition() < -76 || spaceshipGUI.getXPosition() > 788 || spaceshipGUI.getYPosition() < -64 || spaceshipGUI.getYPosition() > 600;
    }

    //@Author Tobias Engblom
    private void activateWrapAround(SpaceshipGUI spaceshipGUI, SpaceshipGUI wrapAroundSpaceshipGUI) {
        if (checkWestPosition(spaceshipGUI)) {
            wrapAroundSpaceshipGUI.getSpaceship().setActive(true);
            wrapAroundSpaceshipGUI.setPosition(788, spaceshipGUI.getYPosition());
        } else if (checkEastPosition(spaceshipGUI)) {
            wrapAroundSpaceshipGUI.getSpaceship().setActive(true);
            wrapAroundSpaceshipGUI.setPosition(-76, spaceshipGUI.getYPosition());
        } else if (checkNorthPosition(spaceshipGUI)) {
            wrapAroundSpaceshipGUI.getSpaceship().setActive(true);
            wrapAroundSpaceshipGUI.setPosition(spaceshipGUI.getXPosition(), 600);
        } else if (checkSouthPosition(spaceshipGUI)) {
            wrapAroundSpaceshipGUI.getSpaceship().setActive(true);
            wrapAroundSpaceshipGUI.setPosition(spaceshipGUI.getXPosition(), -64);
        }
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside west wall
    private boolean checkWestPosition(SpaceshipGUI spaceshipGUI) {
        return spaceshipGUI.getXPosition() <= -12;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside north wall
    private boolean checkNorthPosition(SpaceshipGUI spaceshipGUI) {
        return spaceshipGUI.getYPosition() <= 0;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside east wall
    private boolean checkEastPosition(SpaceshipGUI spaceshipGUI) {
        return spaceshipGUI.getXPosition() >= 724;
    }

    //@Author Tobias Engblom
    //Checks if active spaceship is beginning to go outside south wall
    private boolean checkSouthPosition(SpaceshipGUI spaceshipGUI) {
        return spaceshipGUI.getYPosition() >= 536;
    }
}