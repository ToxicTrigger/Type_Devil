
package mine.typed.GL;


import mine.typed.core.Screen;
import mine.typed.core.interfaces.Game;


/**
 * 게임에서 나타나는 각 화면들을 정의하도록 하는 클래스 입니다.
 * 쉬운 예로 챕터를 떠올리시면 될듯 합니다.
 * @author mrminer
 *
 */
public abstract class GLScreen extends Screen {

	protected final GLGraphics gLGraphics;
	protected final GLGame glgame;

	public GLScreen( final Game game ) {

		super( game );
		this.glgame = (GLGame) game;
		this.gLGraphics = ((GLGame) game).getGLGraphics( );
	}

	@Override
	public abstract void dispose( );

	@Override
	public abstract void pause( );
	@Override
	public abstract void present(float arg0);

	@Override
	public abstract void resent(float arg0);

	@Override
	public abstract void resume( );

	@Override
	public abstract void update(float arg0);

	/**
	 * {@code update()} 에서 호출하세요
	 */
	public abstract void updateTouch();

}
