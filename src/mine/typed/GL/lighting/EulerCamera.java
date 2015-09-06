package mine.typed.GL.lighting;

import javax.microedition.khronos.opengles.GL10;

import mine.typed.core.V3;
import android.opengl.GLU;
import android.opengl.Matrix;

public class EulerCamera
{
	final V3 position = new V3();
	float yaw;
	float pitch;
	float fieldOfView;
	float aspectRatio;
	float near;
	float far;

	public EulerCamera(final float fieldOfView, final float aspectRatio, final float near, final float far)
	{

		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.near = near;
		this.far = far;
	}

	public V3 getPosition()
	{

		return this.position;
	}

	public float getYaw()
	{

		return this.yaw;
	}

	public float getPitch()
	{

		return this.pitch;
	}

	public void setAngles(final float yaw, float pitch)
	{

		if( pitch < -90 )
		{
			pitch = -90;
		}
		if( pitch > 90 )
		{
			pitch = 90;
		}
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void rotate(final float yawInc, final float pitchInc)
	{

		this.yaw += yawInc;
		this.pitch += pitchInc;
		if( this.pitch < -90 )
		{
			this.pitch = -90;
		}
		if( this.pitch > 90 )
		{
			this.pitch = 90;
		}
	}

	public void setMatrices(final GL10 gl)
	{

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, this.fieldOfView, this.aspectRatio, this.near, this.far);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotatef(-this.pitch, 1, 0, 0);
		gl.glRotatef(-this.yaw, 0, 1, 0);
		gl.glTranslatef(-this.position.x, -this.position.y, -this.position.z);
	}

	final float[] matrix = new float[ 16 ];
	final float[] inVec = { 0, 0, -1, 1 };
	final float[] outVec = new float[ 4 ];
	final V3 direction = new V3();

	public V3 getDirection()
	{

		Matrix.setIdentityM(this.matrix, 0);
		Matrix.rotateM(this.matrix, 0, this.yaw, 0, 1, 0);
		Matrix.rotateM(this.matrix, 0, this.pitch, 1, 0, 0);
		Matrix.multiplyMV(this.outVec, 0, this.matrix, 0, this.inVec, 0);
		this.direction.set(this.outVec[ 0 ], this.outVec[ 1 ], this.outVec[ 2 ]);
		return this.direction;
	}
}
