
package mine.typed.GL;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;



public class GLGraphics {

	GLSurfaceView mglView;
	GL10 mgl;
	GLES20 mgl20;

	public GLES20 getGLES20( ) {

		return this.mgl20;
	}

	public void setGLES20(final GLES20 mgl20) {

		this.mgl20 = mgl20;
	}

	public GLGraphics( final GLSurfaceView glView ) {

		this.mglView = glView;

	}

	public GL10 getGL( ) {

		return this.mgl;
	}

	void setGL(final GL10 gl) {

		this.mgl = gl;
	}

	public int getW( ) {

		return this.mglView.getWidth( );
	}
	public int getH( ) {

		return this.mglView.getHeight( );
	}

}
