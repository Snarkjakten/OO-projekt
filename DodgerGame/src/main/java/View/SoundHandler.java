package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class SoundHandler {

    //Plays backgroundmusic on loop
    public void soundPlayer(String soundFilePath, double vol) {

        try {
            File soundPath = new File(soundFilePath);

            if(soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                seVolume(0.01, clip);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Music file missing");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //For all singular instances of sound effects (collisions etc.)
    public void soundFx(String fxFilePath, double vol) {

        try {
            File fxPath = new File(fxFilePath);

            if(fxPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(fxPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                seVolume(vol, clip);
                clip.start();
            } else {
                System.out.println("Fx file missing");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Calculates a double to decibel range and sets volume
    private static void seVolume(double vol, Clip clip){
        FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float decibel = (float) (Math.log(vol) / Math.log(10) * 20);
        gain.setValue(decibel);
    }
}