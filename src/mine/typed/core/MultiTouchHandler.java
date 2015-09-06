
package mine.typed.core;



import java.util.ArrayList;
import java.util.List;

import mine.typed.core.Pool.PoolObjectFactory;
import mine.typed.core.interfaces.Input.TouchEvent;
import mine.typed.core.interfaces.TouchHandler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



/**
 * 멀티터치 핸들러
 * 
 * @author mrminer
 * 
 */
public class MultiTouchHandler implements OnTouchListener , TouchHandler {

	boolean[ ] isTouched = new boolean[ 20 ];
	int[ ] touchX = new int[ 20 ];
	int[ ] touchY = new int[ 20 ];
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>( );
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>( );
	float scaleX;
	float scaleY;

	/**
	 * 
	 * @param view
	 *            터치 좌표를 가져올 View
	 * @param scaleX
	 *            스케일 조정을 위한 스케일 값
	 * @param scaleY
	 *            스케일 조정을 위한 스케일 값
	 */
	public MultiTouchHandler( final View view, final float scaleX, final float scaleY ) {

		final PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>( ) {
			@Override
			public TouchEvent createObject( ) {

				return new TouchEvent( );
			}
		};
		this.touchEventPool = new Pool<TouchEvent>( factory , 100 );
		view.setOnTouchListener( this );

		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	/**
	 * 터치가 되었는지 확인하는 메서드
	 * 
	 * @param View
	 *            터치를 확인하기 위한 View
	 * @param MotionEvent
	 *            터치 이벤트의 타입을 알아내는 인수
	 * @return 터치의 여부를 판단.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouch(final View v, final MotionEvent event) {

		synchronized ( this ) {
			final int action = event.getAction( ) & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction( ) & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			int pointerId = event.getPointerId( pointerIndex );
			TouchEvent touchEvent;

			switch ( action ) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					touchEvent = this.touchEventPool.newOBJ( );
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					touchEvent.pointer = pointerId;
					touchEvent.x = this.touchX[ pointerId ] = (int) (event
							.getX( pointerIndex ) * this.scaleX);
					touchEvent.y = this.touchY[ pointerId ] = (int) (event
							.getY( pointerIndex ) * this.scaleY);
					this.isTouched[ pointerId ] = true;
					this.touchEventsBuffer.add( touchEvent );
					break;

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					touchEvent = this.touchEventPool.newOBJ( );
					touchEvent.type = TouchEvent.TOUCH_UP;
					touchEvent.pointer = pointerId;
					touchEvent.x = this.touchX[ pointerId ] = (int) (event
							.getX( pointerIndex ) * this.scaleX);
					touchEvent.y = this.touchY[ pointerId ] = (int) (event
							.getY( pointerIndex ) * this.scaleY);
					this.isTouched[ pointerId ] = false;
					this.touchEventsBuffer.add( touchEvent );
					break;

				case MotionEvent.ACTION_MOVE:
					final int pointerCount = event.getPointerCount( );
					for ( int i = 0 ; i < pointerCount ; i++ ) {
						pointerIndex = i;
						pointerId = event.getPointerId( pointerIndex );

						touchEvent = this.touchEventPool.newOBJ( );
						touchEvent.type = TouchEvent.TOUCH_DRAGGED;
						touchEvent.pointer = pointerId;
						touchEvent.x = this.touchX[ pointerId ] = (int) (event
								.getX( pointerIndex ) * this.scaleX);
						touchEvent.y = this.touchY[ pointerId ] = (int) (event
								.getY( pointerIndex ) * this.scaleY);
						this.touchEventsBuffer.add( touchEvent );
					}
					break;
			}

			return true;
		}
	}

	@Override
	public boolean isTouchDown(final int pointer) {

		synchronized ( this ) {
			if ( (pointer < 0) || (pointer >= 20) ) {
				return false;
			} else {
				return this.isTouched[ pointer ];
			}
		}
	}


	@Override
	public int getTouchX(final int pointer) {

		synchronized ( this ) {
			if ( (pointer < 0) || (pointer >= 20) ) {
				return 0;
			} else {
				return this.touchX[ pointer ];
			}
		}
	}


	@Override
	public int getTouchY(final int pointer) {

		synchronized ( this ) {
			if ( (pointer < 0) || (pointer >= 20) ) {
				return 0;
			} else {
				return this.touchY[ pointer ];
			}
		}
	}

	/**
	 * @return 터치시 발생한 이벤트의 Type 을 리턴.
	 */
	@Override
	public List<TouchEvent> getTouchEvents( ) {

		synchronized ( this ) {
			final int len = this.touchEvents.size( );
			for ( int i = 0 ; i < len ; i++ ) {
				this.touchEventPool.free( this.touchEvents.get( i ) );
			}
			this.touchEvents.clear( );
			this.touchEvents.addAll( this.touchEventsBuffer );
			this.touchEventsBuffer.clear( );
			return this.touchEvents;
		}
	}
}