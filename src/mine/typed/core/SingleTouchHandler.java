package mine.typed.core;

import java.util.ArrayList;
import java.util.List;

import mine.typed.core.Pool.PoolObjectFactory;
import mine.typed.core.interfaces.Input.TouchEvent;
import mine.typed.core.interfaces.TouchHandler;
import android.view.MotionEvent;
import android.view.View;

/**
 * 싱글 터치 핸들러 ui 조작이나 간단 터치 기능 구현에 사용 합니다.
 * 
 * @author mrminer
 *
 */
public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int tX;
    int tY;
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    float scaleX;
    float scaleY;

    /**
     * 싱글 터치 핸들러를 생성 합니다.
     * 
     * @param view
     * @param scaleX
     * @param scaleY
     */
    public SingleTouchHandler(final View view, final float scaleX,
	    final float scaleY) {

	final PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
	    @Override
	    public TouchEvent createObject() {

		return new TouchEvent();
	    }
	};
	this.touchEventPool = new Pool<TouchEvent>(factory, 100);
	view.setOnTouchListener(this);

	this.scaleX = scaleX;
	this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(final View arg0, final MotionEvent arg1) {

	synchronized (this) {
	    final TouchEvent te = this.touchEventPool.newOBJ();
	    switch (arg1.getAction()) {
	    case MotionEvent.ACTION_DOWN:
		te.type = TouchEvent.TOUCH_DOWN;
		this.isTouched = true;
		break;
	    case MotionEvent.ACTION_MOVE:
	    case MotionEvent.ACTION_UP:
		te.type = TouchEvent.TOUCH_UP;
		this.isTouched = false;
		break;
	    }

	    te.x = this.tX = (int) (arg1.getX() * this.scaleX);
	    te.y = this.tY = (int) (arg1.getY() * this.scaleY);
	    this.touchEventsBuffer.add(te);

	    return true;
	}
    }

    @Override
    public boolean isTouchDown(final int pointer) {

	synchronized (this) {
	    if (pointer == 0) {
		return this.isTouched;
	    } else {
		return false;
	    }
	}
    }

    @Override
    public int getTouchX(final int pointer) {

	synchronized (this) {
	    return this.tX;
	}
    }

    @Override
    public int getTouchY(final int pointer) {

	synchronized (this) {
	    return this.tY;

	}
    }

    @Override
    public List<TouchEvent> getTouchEvents() {

	synchronized (this) {
	    final int len = this.touchEvents.size();
	    for (int i = 0; i < len; i++) {
		this.touchEventPool.free(this.touchEvents.get(i));
	    }
	    this.touchEvents.clear();
	    this.touchEvents.addAll(this.touchEventsBuffer);
	    this.touchEventsBuffer.clear();
	    return this.touchEvents;

	}

    }
}
