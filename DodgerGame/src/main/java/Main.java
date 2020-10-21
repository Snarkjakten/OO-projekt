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

public class Main extends Application implements ITimeObserver {
    //private GameWorld gameWorld;
    //private PausableAnimationTimer gameLoop;

    private final SoundHandler soundHandler = new SoundHandler();
    //private final HighScoreHandler scoreHandler = new HighScoreHandler();
    //private ScoreCalculator scoreCalculator;
    
    private GameLoop gameLoop;
    Window window = new Window(GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight());
    GraphicsContext graphicsContext = window.getGraphicsContext();
    GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
    HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
    ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
    CollisionHandler collisionHandler = new CollisionHandler();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        //gameWorld = GameWorld.getInstance();
        //scoreCalculator = new ScoreCalculator();
        //gameObjects = gameWorld.getGameObjects();
        //Window window = new Window(GameWorld.getPlayingFieldWidth(), GameWorld.getPlayingFieldHeight());
        //GraphicsContext graphicsContext = window.getGraphicsContext();
        MainMenu mainMenu = new MainMenu();
        HighScoreMenu highScoreMenu = new HighScoreMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        GameOverMenu gameOverMenu = new GameOverMenu();
        PauseMenu pauseMenu = new PauseMenu();
        
        gameLoop = new GameLoop();
        
        gameLoop.addTimeObserver(this);
        
        collisionHandler = gameLoop.getCollisionHandler();
        
        
        //WaveManager waveManager = new WaveManager();

        //CollisionHandler collisionHandler = new CollisionHandler();

        LaserGUI laserGUI = LaserGUI.getInstance();
        //GameObjectGUI gameObjectGUI = new GameObjectGUI(graphicsContext);
       // HealthBarGUI healthBarGUI = new HealthBarGUI(graphicsContext);
        //ShieldGUI shieldGUI = new ShieldGUI(graphicsContext);
        //LaserGUI laserGUI = new LaserGUI(graphicsContext, 10, true);
        BackgroundView backgroundView = new BackgroundView(graphicsContext);
        ITimeObserver timeView = new TimeView(graphicsContext);

        ViewController vc = new ViewController(window, mainMenu, highScoreMenu, characterMenu, gameOverMenu, stage, gameLoop, gameObjectGUI, pauseMenu);
        stage.setTitle("Space Dodger");

        Scene mainMenuScene = new Scene(mainMenu.getRoot());
        stage.setScene(mainMenuScene);
        //Removes option to change size of program window
        stage.setResizable(false);
        stage.show();

        /**
         * Handle key pressed
         * @author Irja Vuorela
         */
        KeyController keyController = new KeyController(stage);
        stage.getScene().setOnKeyPressed(
                keyController::handleKeyPressed);

        /**
         * Handle key released
         * @author Irja Vuorela
         */
        stage.getScene().setOnKeyReleased(
                keyController::handleKeyReleased
        );

        window.init();
        //@Author tobbe
        
        soundHandler.musicPlayer(GameObjectsSounds.getBackgroundMusicPath());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void updateGUI(double deltaTime) {
        List<AbstractGameObject> gameObjects = GameWorld.getInstance().getGameObjects();
        
        for (AbstractGameObject gameObject : gameObjects) {
            gameObjectGUI.drawImage(gameObject.getHitBoxes(), gameObject.getClass(), gameObject.getWidth(), gameObject.getHeight(), );
        }
        
        healthBarGUI.drawHealthBar(GameWorld.getInstance().getSpaceship().getHp(), GameWorld.getInstance().getSpaceship().getMaxHp());
        
        shieldGUI.drawImage(GameWorld.getInstance().getSpaceship(), deltaTime);
    }
    
    private void updateSound(Class c) {
        soundHandler.playSound(c);
    }

    @Override
    public void actOnEvent(long time, double deltaTime) {
        updateGUI(deltaTime);
        updateSound(); // TODO: 2020-10-21 flytta till annan actonevent 
    }
}
