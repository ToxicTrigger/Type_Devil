
package mine.typed.core.Type;



import java.util.List;

import mine.typed.core.MultiTouchHandler;
import mine.typed.core.SingleTouchHandler;
import mine.typed.core.interfaces.Input;
import mine.typed.core.interfaces.TouchHandler;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;


/**
 * 1차적으로 인풋을 관리해주는 클래스이다.
 * @author mrminer
 *
 */
public class TypeInput implements Input {

	TouchHandler touchHandler;

	@SuppressWarnings("deprecation")
	public TypeInput( final Context context, final View view, final float scaleX, final float scaleY ) {

		if ( Integer.parseInt( VERSION.SDK ) < 5 ) {
			this.touchHandler = new SingleTouchHandler( view , scaleX , scaleY );
		} else {
			this.touchHandler = new MultiTouchHandler( view , scaleX , scaleY );
		}
	}

	@Override
	public boolean isTouchDown(final int pointer) {

		return this.touchHandler.isTouchDown( pointer );
	}

	@Override
	public int getTouchX(final int pointer) {

		return this.touchHandler.getTouchX( pointer );
	}

	@Override
	public int getTouchY(final int pointer) {

		return this.touchHandler.getTouchY( pointer );
	}

	@Override
	public List<TouchEvent> getTouchEvents( ) {

		return this.touchHandler.getTouchEvents( );
	}

	@Override
	public float getAccelX( ) {


		Log.w( "<TypeInput>" , "getAccelX() is not makeing" );

		return 0;
	}

	@Override
	public float getAccelY( ) {


		Log.w( "<TypeInput>" , "getAccelY() is not makeing" );

		return 0;
	}

	@Override
	public float getAccelZ( ) {


		Log.w( "<TypeInput>" , "getAccelZ() is not makeing" );

		return 0;
	}

	@Override
	public List<KeyEvent> getKeyEvents( ) {


		Log.w( "<TypeInput>" , "getKeyEvents() is not makeing" );

		return null;
	}

	@Override
	public boolean isKeyPressed(int keyCode) {
		Log.w( "<TypeInput>" , "isKeyPressed(int keyCode) is not makeing" );
		return false;
	}

}