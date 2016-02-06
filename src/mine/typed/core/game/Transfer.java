package mine.typed.core.game;

import mine.typed.core.V2;

public class Transfer {
    public static final int AXIS_Left = 0;
    public static final int AXIS_Up = 1;

    public static void move(int V2UpStopDown, float delta, float accel, V2 pos, int axis) {
	float v = delta * accel * V2UpStopDown;
	if (axis == AXIS_Left)
	    pos.add(v, 0);
	else
	    pos.add(0, v);
    }
}
