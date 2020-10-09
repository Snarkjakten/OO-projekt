package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundHandler {

    public void soundPlayer(String soundFilePath) {

        try {
            File soundPath = new File(soundFilePath);

            if(soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Music file missing");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void soundFx(String fxFilePath) {

        try {
            File fxPath = new File(fxFilePath);

            if(fxPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(fxPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Fx file missing");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}