package mine.typed.GL;

import mine.typed.core.interfaces.Game;

/**
 * 게임에서 나타나는 각 화면들을 정의하도록 하는 클래스 입니다. 쉬운 예로 챕터를 떠올리시면 될듯 합니다.
 * 
 * @author mrminer
 *
 */
public abstract class GLScreen extends Screen {

    protected final GLGraphics gLGraphics;
    protected final GLGame glgame;

    public GLScreen(final Game game,int MinPriority) {

	super(game, MinPriority);
	this.glgame = (GLGame) game;
	if(game != null)
	    this.gLGraphics = ((GLGame) game).getGLGraphics();
	else
	    this.gLGraphics = null;
    }

    @Override
    public abstract void dispose();

    @Override
    public abstract void pause();
    
    @Override
    public abstract void resent(float arg0);

    @Override
    public abstract void resume();

    // present 보다 먼저 호출 됨
    @Override
    public abstract void update(float arg0);

    @Override
    public abstract void lateUpdate(float arg0);

    /**
     * {@code update()} 에서 호출하세요
     */
    @Override
    public abstract void updateTouch();

}
