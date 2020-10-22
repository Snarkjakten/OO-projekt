package Interfaces;

public interface ITimeObserver {

    /**
     * @param time      an amount of time
     * @param deltaTime a difference in time
     */
    void actOnTimeEvent(long time, double deltaTime);
}
