package mine.typed.core.interfaces;

public interface Music {

    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volumeL, float volumeR);

    public boolean isPlay();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();

}
