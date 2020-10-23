import Controller.AnimationController;
import Controller.KeyController;
import Controller.ViewController;
import Model.*;
import Interfaces.*;
import Model.Entities.AbstractGameObject;
import Model.Handlers.CollisionHandler;
import View.GUI.*;
import View.Menu.CharacterMenu;
import View.Menu.GameOverMenu;
import View.Menu.HighScoreMenu;
import View.Menu.MainMenu;
import View.Sound.GameObjectsSounds;
import View.Sound.SoundHandler;
import View.GameWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application implements ITimeObserver, IGameObjectObserver {

    private final SoundHandler soundHandler = new SoundHandler();

    private AnimationController animationController;

    private Game game;
    GameWindow gameWindow = new GameWindow(GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight());
    CollisionHandler collisionHandler;

    GraphicsContext graphicsContext = gameWindow.getGraphicsContext();
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

        game = new Game();
        animationController = new AnimationController(game);
        ViewController vc = new ViewController(gameWindow, mainMenu, highScoreMenu, characterMenu, gameOverMenu, stage, animationController, gameObjectGUI);
        collisionHandler = game.getCollisionHandler();

        // observers
        game.addTimeObserver(this);
        collisionHandler.addGameObjectObserver(this);
        game.addGameOverObserver(vc);

        stage.setTitle("Space Dodger"); // todo: move to window

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);

        //todo: move to window
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        // handle movement key inputs
        handleMovementKeys(stage);

        gameWindow.init();

        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Updates all the images
     *
     * @param time the elapsed time in the game loop
     * @author Everyone
     */
    private void updateGUI(long time) {
        List<AbstractGameObject> gameObjects = GameWorld.getInstance().getGameObjects();

        playingFieldGUI.drawBackground(0, 0, GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight(), 0);

        timerGUI.drawImage(time);

        for (AbstractGameObject gameObject : gameObjects) {
            gameObjectGUI.drawImage(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight(), animationController.getAnimationTime());
        }

        healthBarGUI.drawHealthBar(GameWorld.getInstance().getSpaceship().getHp(), GameWorld.getInstance().getSpaceship().getMaxHp());

        shieldGUI.drawImage(GameWorld.getInstance().getSpaceship(), animationController.getAnimationTime());
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
        KeyController keyController = new KeyController();
        stage.getScene().setOnKeyPressed(
                keyController::handleKeyPressed);

        // handle key released
        stage.getScene().setOnKeyReleased(
                keyController::handleKeyReleased
        );
    }

    @Override
    public void actOnTimeEvent(long time) {
        updateGUI(time);
    }

    @Override
    public void actOnGameObjectEvent(Class c, int amount) {
        updateSound(c);
    }
}
