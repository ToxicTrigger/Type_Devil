package mine.typed.GL.lighting;

import javax.microedition.khronos.opengles.GL10;

import mine.typed.GL.GLGraphics;

public class Light
{
	final float[] ambient;
	final float[] diffuse;
	final float[] position;
	final GLGraphics glGraphics;

	public Light(final GLGraphics glGraphics, final boolean isDirectional)
	{

		this.glGraphics = glGraphics;
		this.ambient = new float[] { 0.2f, 0.2f, 0.2f, 1.0f };
		this.diffuse = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
		this.position = new float[] { 0, 0, 0, isDirectional ? 0 : 1 };
	}

	public boolean isDirectional()
	{

		return this.position[ 3 ] == 0;
	}

	public void setAmbient(final float r, final float g, final float b)
	{

		this.ambient[ 0 ] = r;
		this.ambient[ 1 ] = g;
		this.ambient[ 2 ] = b;
	}

	public void setDiffuse(final float r, final float g, final float b)
	{

		this.diffuse[ 0 ] = r;
		this.diffuse[ 1 ] = g;
		this.diffuse[ 2 ] = b;
	}

	public void setPosition(final float x, final float y, final float z)
	{

		this.position[ 0 ] = x;
		this.position[ 1 ] = y;
		this.position[ 2 ] = z;
	}

	public void enable(final int lightNum)
	{

		final GL10 gl = this.glGraphics.getGL();
		gl.glEnable(lightNum);
		gl.glLightfv(lightNum, GL10.GL_AMBIENT, this.ambient, 0);
		gl.glLightfv(lightNum, GL10.GL_DIFFUSE, this.diffuse, 0);
		gl.glLightfv(lightNum, GL10.GL_POSITION, this.position, 0);
	}
}
