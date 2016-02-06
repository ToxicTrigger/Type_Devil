package mine.typed.GL.renderer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Renderer implements TypeRenderer {

	public final int Priority;
	
	public Renderer(int priority){
		Priority = priority;
	}
	
	@Override
	public abstract void draw(float delta, GL10 gl);
	
}