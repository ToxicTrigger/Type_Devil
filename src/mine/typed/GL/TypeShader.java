package mine.typed.GL;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

public class TypeShader
{

	/**
	 * 블렌딩 연산시 밝은 느낌 위주로 하며 특징은 반투명 영역을 빛과 같은 성질로 렌더링을 합니다.
	 */
	static public final int TYPE_LIGHT = 1;
	/**
	 * 블렌딩 연산시 어두운 느낌 위주로 하며 특징은 반투명 영역을 물감과 같은 성질로 렌더링을 합니다.
	 */
	static public final int TYPE_DARK = 2;
	/**
	 * 전체적으로 보면 TYPE_LIGHT 와 비슷한 느낌이지만 보이지 않는 픽셀에 대한 연산을 행하지 않도록 합니다.
	 */
	static public final int TYPE_CLEAN = 3;

	static public GL10 gl10;

	public static GLES20 gles20;

	static public GL10 getGl10()
	{

		return TypeShader.gl10;
	}

	static public void setGl10(final GL10 gl10)
	{

		TypeShader.gl10 = gl10;
	}

	static public GLES20 getGles20()
	{

		return TypeShader.gles20;
	}

	static public void setGles20(final GLES20 gles20)
	{

		TypeShader.gles20 = gles20;
	}

	/**
	 * 초기화를 해줍니다. 버퍼를 비우고 2D 텍스쳐를 활성화 하며 블랜딩 기능을 활성화 합니다.
	 * 
	 * @param gl
	 * @throws NullGLException
	 *             GL 이 생성되지 않았을때 내보냅니다.
	 */
	static public void init(GL10 gl) throws NullGLException
	{
		if( gl == null )
		{
			throw new NullGLException();
		} else
		{
			TypeShader.Clear(gl);
			TypeShader.tex2D(gl);
			TypeShader.enableBlend(gl);
		}
	}

	static public void Clear(GL10 gl)
	{
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);
		TypeShader.clearColor(gl, 0, 0, 0, 1);
	}

	static public void clearColor(GL10 gl, final float r, final float g, final float b, final float a)
	{
		gl.glClearColor(r, g, b, a);
	}

	static public void tex2D(GL10 gl)
	{
		gl.glEnable(GL10.GL_TEXTURE_2D);
	}

	static public void enableBlend(GL10 gl)
	{
		gl.glEnable(GL10.GL_BLEND);
	}

	static public void enableAlphaTest(GL10 gl)
	{
		gl.glEnable(GL10.GL_ALPHA_TEST);
	}

	static public void enableAntiA(GL10 gl)
	{
		gl.glEnable(GL10.GL_POINT_SMOOTH);
		gl.glEnable(GL10.GL_LINE_SMOOTH);
		gl.glEnable(GL10.GL_POLYGON_SMOOTH_HINT);
	}

	static public void enableShaderType(GL10 gl, final int TYPE)
	{

		switch (TYPE)
		{
		case TYPE_LIGHT:
			gl.glBlendFunc(GLES20.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA | GLES20.GL_ZERO);
			break;

		case TYPE_DARK:
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			break;

		case TYPE_CLEAN:
			gl.glBlendFunc(GLES20.GL_DST_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
			break;

		default:
			gl.glBlendFunc(GLES20.GL_DST_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
			break;
		}

	}

}
