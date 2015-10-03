package mine.typed.core.interfaces;

import mine.typed.core.Screen;

public interface Game {

    public Input getInput();

    public FileIO getFileIO();

    public Graphic getGraphic();

    public Audio getAudio();

    /**
     * 보여질 스크린을 정합니다.
     * 
     * @param screen
     */
    public void setScreen(Screen screen);

    /**
     * 
     * @return 현재 스크린이 호출되기 전 스크린을 불러냅니다.
     */
    public Screen getCurrentScreen();

    /**
     * 
     * @return 처음 보여진 스크린을 가져옵니다.
     */
    public Screen getStartScreen();

}
