package mine.typed.core.interfaces;

public interface Sound {

    public enum State {
	Init, playing, pause, end
    }

    /**
     * 소리를 재생합니다.
     * 
     * @param volumeL
     * @param volumeR
     */
    public void play(float volumeL, float volumeR);

    public void setVolume(float l, float r);

    /**
     * 해제합니다.
     */
    public void dispose();

}
