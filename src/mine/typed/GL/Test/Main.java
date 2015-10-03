package mine.typed.GL.Test;

import mine.typed.core.V2;
import mine.typed.core.game.DynamicGameObject;
import mine.typed.core.game.Transfer;

public class Main {

    public static void main(String[] arg) {
	DynamicGameObject a, b;

	long startTime = System.nanoTime();

	a = new DynamicGameObject(0, 0, 20);
	b = new DynamicGameObject(10, 0, 10);

	for (int i = 0; i < 20; i++) {
	    final float delta = (System.nanoTime() - startTime) / 1000000000.0f;

	    startTime = System.nanoTime();

	    Transfer.move(V2.Up, delta, 50, a.position, Transfer.AXIS_Left);
	    Transfer.move(V2.Up, delta, 2, b.position, Transfer.AXIS_Up);

	}
	System.out.println("a.x = " + a.position.x);
	System.out.println("b.y = " + b.position.y);
    }
}
