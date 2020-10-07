import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class PausableAnimationTimer extends AnimationTimer {

    private long pauseStart;
    private long animationStart;
    private DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    private boolean isPaused;
    private boolean isActive;

    private boolean pauseScheduled;
    private boolean playScheduled;
    private boolean restartScheduled;

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isRestartScheduled() {
        return restartScheduled;
    }

    public DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    public void pause() {
        if (!isPaused) {
            pauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused) {
            playScheduled = true;
        }
    }

    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
        animationDuration.set(0);
    }

    @Override
    public void handle(long now) {
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            animationStart += (now - pauseStart);
            isPaused = false;
            playScheduled = false;
        }

        if (restartScheduled) {
            isPaused = false;
            animationStart = now;
            restartScheduled = false;
        }

        if (!isPaused) {
            long animDuration = now - animationStart;
            animationDuration.set(animDuration / 1e9);
            tick(animDuration);
        }
    }

    public abstract void tick(long relativeNow);
}
