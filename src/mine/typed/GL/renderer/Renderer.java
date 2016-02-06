<<<<<<< HEAD
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
=======
package mine.typed.GL.renderer;

public abstract class Renderer implements TypeRenderer {

	public final int Priority;
	
	public Renderer(int priority){
		Priority = priority;
	}
	@Override
	public abstract void draw(float delta);
	
}
>>>>>>> b9d44b4b47440ece10aea5b44e79c5df179921cc
