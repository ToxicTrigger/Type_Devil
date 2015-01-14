package mine.typed.GL;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

import mine.tools.debug.TypeLogger;

/**
 * 초기 설정값을 정할수 있습니다.
 * FBO.W , FBO.H 를 수정하세요.
 * 기초값은 W = 800 , H = 480 입니다.
 * @author mrminer
 *
 */
public abstract class FBO {
	public static boolean mContextSupportsFrameBufferObject;
	public static int W = 800;
	public static int H = 480;
	public static TypeLogger debug = TypeLogger.getInstance( );
	public static int mTargetTexture;
	public static int mFramebuffer;
	public static int mWidth;
	public static int mHeight;

	private static final boolean DEBUG_RENDER_OFFSCREEN_ONSCREEN = false;


	public FBO( GLGame glga , int ScreenW , int ScreenH ) {
		FBO.mHeight = ScreenW;
		FBO.mWidth = ScreenH;
	}

	public static void onSurfaceChanged(GL10 gl, int width, int height) {
		FBO.checkGLError(gl);
		FBO.mWidth = width;
		FBO.mHeight = height;
		// gl.glViewport(0, 0, width, height);
	}

	static void checkGLError(GL gl) {
		final int error = ((GL10) gl).glGetError();
		if (error != GL10.GL_NO_ERROR) {
			throw new RuntimeException("GLError 0x" + Integer.toHexString(error));
		}
	}

	public static int createTargetTexture(GL10 gl, int width, int height) {
		int texture;
		final int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		texture = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, width, height, 0,
				GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, null);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,
				GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);
		;            return texture;
	}

	public static int createFrameBuffer(GL10 gl, int width, int height, int targetTextureId) {
		final GL11ExtensionPack gl11ep = (GL11ExtensionPack) gl;
		int framebuffer;
		final int[] framebuffers = new int[1];
		gl11ep.glGenFramebuffersOES(1, framebuffers, 0);
		framebuffer = framebuffers[0];
		gl11ep.glBindFramebufferOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES, framebuffer);

		int depthbuffer;
		final int[] renderbuffers = new int[1];
		gl11ep.glGenRenderbuffersOES(1, renderbuffers, 0);
		depthbuffer = renderbuffers[0];

		gl11ep.glBindRenderbufferOES(GL11ExtensionPack.GL_RENDERBUFFER_OES, depthbuffer);
		gl11ep.glRenderbufferStorageOES(GL11ExtensionPack.GL_RENDERBUFFER_OES,
				GL11ExtensionPack.GL_DEPTH_COMPONENT16, width, height);
		gl11ep.glFramebufferRenderbufferOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES,
				GL11ExtensionPack.GL_DEPTH_ATTACHMENT_OES,
				GL11ExtensionPack.GL_RENDERBUFFER_OES, depthbuffer);

		gl11ep.glFramebufferTexture2DOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES,
				GL11ExtensionPack.GL_COLOR_ATTACHMENT0_OES, GL10.GL_TEXTURE_2D,
				targetTextureId, 0);
		final int status = gl11ep.glCheckFramebufferStatusOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES);
		if (status != GL11ExtensionPack.GL_FRAMEBUFFER_COMPLETE_OES) {
			throw new RuntimeException("Framebuffer is not complete: " + Integer.toHexString(status));
		}
		gl11ep.glBindFramebufferOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES, 0);
		return framebuffer;
	}


	static public boolean checkIfContextSupportsFrameBufferObject(GL10 gl) {
		return FBO.checkIfContextSupportsExtension(gl, "GL_OES_framebuffer_object");
	}
	static public boolean checkIfContextSupportsExtension(GL10 gl, String extension) {
		final String extensions = " " + gl.glGetString(GL10.GL_EXTENSIONS) + " ";

		return extensions.indexOf(" " + extension + " ") >= 0;
	}



	public static void onSurfaceCreated(GLGame glga , GL10 gl , javax.microedition.khronos.egl.EGLConfig config){
		FBO.mContextSupportsFrameBufferObject = FBO.checkIfContextSupportsFrameBufferObject(gl);

		if (FBO.mContextSupportsFrameBufferObject) {
			FBO.mTargetTexture = FBO.createTargetTexture(gl, FBO.mWidth, FBO.mHeight);
			FBO.mFramebuffer = FBO.createFrameBuffer(gl, FBO.mWidth, FBO.mHeight, FBO.mTargetTexture);

		}
	}

	public void onDrawFrame(GL10 gl) {
		FBO.checkGLError(gl);
		if (FBO.mContextSupportsFrameBufferObject) {
			final GL11ExtensionPack gl11ep = (GL11ExtensionPack) gl;
			if (FBO.DEBUG_RENDER_OFFSCREEN_ONSCREEN) {
				this.drawOffscreenImage(gl);
			} else {
				gl11ep.glBindFramebufferOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES, FBO.mFramebuffer);
				this.drawOffscreenImage(gl);
				gl11ep.glBindFramebufferOES(GL11ExtensionPack.GL_FRAMEBUFFER_OES, 0);
				this.drawOnscreen(gl);
			}
		} else {

			gl.glClearColor(1,0,0,0);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		}
	}

	/**
	 * 화면에 보여질 공간을 그립니다.
	 * @param gl
	 */
	protected abstract void drawOnscreen(GL10 gl);

	/**
	 * 백버퍼 입니다.
	 * @param gl
	 */
	protected abstract void drawOffscreenImage(GL10 gl);


}
