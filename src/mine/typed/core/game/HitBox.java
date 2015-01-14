package mine.typed.core.game;

import mine.typed.core.Rectangle;
import mine.typed.core.V2;

/**
 * 충돌박스 입니다.
 * 2가지의 상태를 가집니다.
 * @author mrminer
 *
 */
public class HitBox extends Rectangle {

	/**
	 * 충돌에 대하여 검사하지 않습니다.
	 */
	public final static int STATE_UNCHECK = 0;
	/**
	 * 충돌을 검사 합니다.
	 */
	public final static int STATE_CHECK = 1;

	private int state;

	public int getState( ) {
		return this.state;
	}

	public void setState( int state ) {
		this.state = state;
	}

	public HitBox( float x , float y , float width , float height ) {
		super( x , y , width , height );
		this.state = HitBox.STATE_CHECK;
	}

	public HitBox( V2 v , float width , float height ) {
		super( v , width , height );
		this.state = HitBox.STATE_CHECK;
	}


}
