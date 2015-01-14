
package mine.typed.GL.lighting;



import javax.microedition.khronos.opengles.GL10;



public class PointLight {
	float[ ] ambient = { 0.2f , 0.2f , 0.2f , 1.0f };
	float[ ] diffuse = { 1.0f , 1.0f , 1.0f , 1.0f };
	float[ ] specular = { 0.0f , 0.0f , 0.0f , 1.0f };
	float[ ] position = { 0 , 0 , 0 , 1 };
	int lastLightId = 0;

	public void setAmbient(final float r, final float g, final float b, final float a) {

		this.ambient[ 0 ] = r;
		this.ambient[ 1 ] = g;
		this.ambient[ 2 ] = b;
		this.ambient[ 3 ] = a;
	}

	public void setDiffuse(final float r, final float g, final float b, final float a) {

		this.diffuse[ 0 ] = r;
		this.diffuse[ 1 ] = g;
		this.diffuse[ 2 ] = b;
		this.diffuse[ 3 ] = a;
	}

	public void setSpecular(final float r, final float g, final float b, final float a) {

		this.specular[ 0 ] = r;
		this.specular[ 1 ] = g;
		this.specular[ 2 ] = b;
		this.specular[ 3 ] = a;
	}

	public void setPosition(final float x, final float y, final float z) {

		this.position[ 0 ] = x;
		this.position[ 1 ] = y;
		this.position[ 2 ] = z;
	}

	public void enable(final GL10 gl, final int lightId) {

		gl.glEnable( lightId );
		gl.glLightfv( lightId , GL10.GL_AMBIENT , this.ambient , 0 );
		gl.glLightfv( lightId , GL10.GL_DIFFUSE , this.diffuse , 0 );
		gl.glLightfv( lightId , GL10.GL_SPECULAR , this.specular , 0 );
		gl.glLightfv( lightId , GL10.GL_POSITION , this.position , 0 );
		this.lastLightId = lightId;
	}

	public void disable(final GL10 gl) {

		gl.glDisable( this.lastLightId );
	}
}
