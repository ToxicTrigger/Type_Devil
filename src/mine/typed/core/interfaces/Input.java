package mine.typed.core.interfaces;

import java.util.List;

public interface Input
{

	public static class KeyEvent
	{

		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;

		public int type, keyCode;
		public char keyChar;

	}

	public static class TouchEvent
	{
		// 터치 이벤트 정의 상수
		// 손가락이 터치 스크린에 닿았을때
		public static final int TOUCH_DOWN = 0;
		// 손가락이 떨어졌을때
		public static final int TOUCH_UP = 1;
		// 손가락이 터치 스크린에 닿은 상태에서 움직일때
		public static final int TOUCH_DRAGGED = 2;

		public int type, x, y, pointer;

	}

	// 키 코드 값을 가져와 해당 값이 맞는지 판단한다.
	public boolean isKeyPressed(int keyCode);

	// 특정 포인트가 다운인지 알아낸다.
	public boolean isTouchDown(int pointer);

	// 터치 좌표를 반환
	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	// 가속도계 축의 대한 가속도 값을 반환.
	public float getAccelX();

	public float getAccelY();

	public float getAccelZ();

	// pool
	public List<KeyEvent> getKeyEvents();

	public List<TouchEvent> getTouchEvents();

}
