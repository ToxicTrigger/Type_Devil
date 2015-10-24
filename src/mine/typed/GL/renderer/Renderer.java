package mine.typed.GL.renderer;

public abstract class Renderer implements TypeRenderer {

	public final int Priority;
	
	public Renderer(int priority){
		Priority = priority;
	}
	@Override
	public abstract void draw(float delta);
	
}
