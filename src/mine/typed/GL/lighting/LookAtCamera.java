package mine.typed.GL.lighting;

import javax.microedition.khronos.opengles.GL10;

import mine.typed.core.V3;
import android.opengl.GLU;

public class LookAtCamera {
    final V3 position;
    final V3 up;
    final V3 lookAt;
    float fieldOfView;
    float aspectRatio;
    float near;
    float far;

    public LookAtCamera(final float fieldOfView, final float aspectRatio,
	    final float near, final float far) {

	this.fieldOfView = fieldOfView;
	this.aspectRatio = aspectRatio;
	this.near = near;
	this.far = far;

	this.position = new V3();
	this.up = new V3(0, 1, 0);
	this.lookAt = new V3(0, 0, -1);
    }

    public V3 getPosition() {

	return this.position;
    }

    public V3 getUp() {

	return this.up;
    }

    public V3 getLookAt() {

	return this.lookAt;
    }

    public void setMatrices(final GL10 gl) {

	gl.glMatrixMode(GL10.GL_PROJECTION);
	gl.glLoadIdentity();
	GLU.gluPerspective(gl, this.fieldOfView, this.aspectRatio, this.near, this.far);
	gl.glMatrixMode(GL10.GL_MODELVIEW);
	gl.glLoadIdentity();
	GLU.gluLookAt(gl, this.position.x, this.position.y, this.position.z, this.lookAt.x, this.lookAt.y, this.lookAt.z, this.up.x, this.up.y, this.up.z);
    }
}
