package mine.typed.GL.renderer;

import javax.microedition.khronos.opengles.GL10;

public interface TypeRenderer extends Runnable{
	public void draw(float delta, GL10 gl);
}