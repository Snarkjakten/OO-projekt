package View.Sound;

public class GameObjectsSounds {

    //Background music file
    private static String backgroundMusicPath = "src/main/resources/277325__shadydave__time-break (1).wav";

    //Filepaths for soundFX files
    private static String asteroidSound = "src/main/resources/448226__inspectorj__explosion-8-bit-01 (2).wav";
    private static String shieldSound = "src/main/resources/514289__mrthenoronha__alien-sound-2-8-bit (1).wav";
    private static String healthSound = "src/main/resources/368691__fartbiscuit1700__8-bit-arcade-video-game-start-sound-effect-gun-reload-and-jump.wav";
    private static String slowDebuffSound = "src/main/resources/146731__leszek-szary__game-fail.wav";

    public static String getBackgroundMusicPath() {
        return backgroundMusicPath;
    }

    public static String getAsteroidSound() {
        return asteroidSound;
    }

    public static String getShieldSound() {
        return shieldSound;
    }

    public static String getHealthSound() {
        return healthSound;
    }

    public static String getSlowDebuffSound() { return slowDebuffSound; }
}
