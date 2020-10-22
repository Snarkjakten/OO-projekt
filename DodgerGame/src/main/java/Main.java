import Controller.KeyController;
import Controller.ViewController;
import Model.*;
import Interfaces.*;
import Model.Movement.AbstractGameObject;
import Model.Movement.CollisionHandler;
import View.*;
import View.Sound.GameObjectsSounds;
import View.Sound.SoundHandler;
import View.Window;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application implements ITimeObserver, IGameObjectObserver {

    private final SoundHandler soundHandler = new SoundHandler();

    private GameLoop gameLoop;
    Window window = new Window(GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight());
    CollisionHandler collisionHandler = new CollisionHandler();

    GraphicsContext graphicsContext = window.getGraphicsContext();
    PlayingFieldGUI playingFieldGUI = new PlayingFieldGUI(graphicsContext);
    GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
    HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
    ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
    TimerGUI timerGUI = new TimerGUI(graphicsContext);

    @Override
    public void start(Stage stage) throws Exception {
        // Menus
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();

        gameLoop = new GameLoop();
        ViewController vc = new ViewController(window, mainMenu, highScoreMenu, characterMenu, gameOverMenu, stage, gameLoop.getGameLoop(), gameObjectGUI);
        collisionHandler = gameLoop.getCollisionHandler();

        // observers
        gameLoop.addTimeObserver(this);
        collisionHandler.addGameObjectObserver(this);
        gameLoop.addGameOverObserver(vc);

        stage.setTitle("Space Dodger"); // todo: move to window

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);

        //todo: move to window
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        // handle movement key inputs
        handleMovementKeys(stage);

        window.init();

        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Updates all the images
     *
     * @param time      the elapsed time in the game loop
     * @param deltaTime the length of a frame in the game loop
     * @author Everyone
     */
    private void updateGUI(long time, double deltaTime) {
        List<AbstractGameObject> gameObjects = GameWorld.getInstance().getGameObjects();

        playingFieldGUI.drawBackground(0, 0, GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight(), 0);

        timerGUI.drawImage(time);

        for (AbstractGameObject gameObject : gameObjects) {
            gameObjectGUI.drawImage(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight(), deltaTime);
        }

        healthBarGUI.drawHealthBar(GameWorld.getInstance().getSpaceship().getHp(), GameWorld.getInstance().getSpaceship().getMaxHp());

        shieldGUI.drawImage(GameWorld.getInstance().getSpaceship(), deltaTime);
    }

    /**
     * Updates the sounds
     *
     * @param c the class type associated with the sound to be played
     */
    private void updateSound(Class c) {
        soundHandler.playSound(c);
    }

    /**
     * Handle movement key inputs
     *
     * @author Irja Vuorela
     */
    private void handleMovementKeys(Stage stage) {
        // handle key pressed
        KeyController keyController = new KeyController(stage);
        stage.getScene().setOnKeyPressed(
                keyController::handleKeyPressed);

        // handle key released
        stage.getScene().setOnKeyReleased(
                keyController::handleKeyReleased
        );
    }

    @Override
    public void actOnTimeEvent(long time, double deltaTime) {
        updateGUI(time, deltaTime);
    }

    @Override
    public void actOnGameObjectEvent(Class c) {
        updateSound(c);
    }
}
