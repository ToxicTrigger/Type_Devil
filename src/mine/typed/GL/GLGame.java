package mine.typed.GL;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mine.tools.debug.TypeLogger;
import mine.typed.core.Type.TypeAudio;
import mine.typed.core.Type.TypeFileIO;
import mine.typed.core.Type.TypeInput;
import mine.typed.core.interfaces.Audio;
import mine.typed.core.interfaces.FileIO;
import mine.typed.core.interfaces.Game;
import mine.typed.core.interfaces.Input;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 어플리케이션의 Activity 입니다. TypeD 는 이 클래스를 상속받은 Activity 를 찾아 동작합니다.
 * 
 * @author mrminer
 *
 */
public abstract class GLGame extends Activity implements Game, Renderer {

    enum GLGameState {
	Initialized, Running, Paused, Finished, Idle
    }

    GLSurfaceView glView;
    GLGraphics GLGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    public GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    public static long startTime = System.nanoTime();
    public static float delta;

    private static TypeLogger logger = TypeLogger.getInstance();

    private static boolean isPublicModeUsable;

    public FBO fbo;

    public static boolean isCreated = true;

    @Override
    public void onCreate(final Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	PlatformInfo.isRunOnAndroid = true;

	// Lua 의 사용 여부 설정
	// DevLevel 에서는 사용함. public 레벨에선 상속후 불러와 따로 설정하면 됨

	// --------------------

	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	this.getWindow()
		.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	this.getWindow()
		.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	this.glView = new GLSurfaceView(this);
	this.glView.setRenderer(this);
	this.setContentView(this.glView);

	this.GLGraphics = new GLGraphics(this.glView);
	this.fileIO = new TypeFileIO(this.getAssets());
	this.audio = new TypeAudio(this);
	this.input = new TypeInput(this, this.glView, 1, 1);

    }


    @Override
    public void onResume() {
	super.onResume();
	TextureManager.getInstance().reloadAll();
	this.glView.onResume();
    }

    @Override
    public void onSurfaceCreated(final GL10 gl, final EGLConfig config) {
	this.GLGraphics.setGL(gl);

	synchronized (this.stateChanged) {
	    if (this.state == GLGameState.Initialized) {
		this.screen = this.getStartScreen();
	    }
	    this.state = GLGameState.Running;
	    this.screen.resume();
	    startTime = System.nanoTime();
	}

    }

    @Override
    public void onSurfaceChanged(final GL10 gl, final int width,
	    final int height) {
    }

    @Override
    public void onDrawFrame(final GL10 gl) {
	GLGameState state = null;
	synchronized (this.stateChanged) {
	    state = this.state;
	}

	if (state == GLGameState.Running) {
	    delta = (System.nanoTime() - GLGame.startTime) / 1000000000.0f;
	    final float del = delta;
	    GLGame.startTime = System.nanoTime();
	    
	    if(screen.time >= screen.getMessageTick()){
		screen.getMessage();
		screen.time = 0;
	    }else{
		screen.time += del;
	    }

	    this.screen.updateFinally(del);
	    
<<<<<<< HEAD
	    this.screen.callRenderers(del, this.getGLGraphics().getGL());
=======
	    this.screen.callRenderers(del);
>>>>>>> b9d44b4b47440ece10aea5b44e79c5df179921cc

	    this.screen.lateUpdate(del);
	}

	if (state == GLGameState.Paused) {
	    this.screen.pause();
	    synchronized (this.stateChanged) {
		this.state = GLGameState.Idle;
		this.stateChanged.notifyAll();
	    }
	}

	if (state == GLGameState.Finished) {
	    this.screen.pause();
	    this.screen.dispose();
	    synchronized (this.stateChanged) {
		this.state = GLGameState.Idle;
		this.stateChanged.notifyAll();
	    }
	}
    }

    @Override
    public void onPause() {

	synchronized (this.stateChanged) {
	    if (this.isFinishing()) {
		this.state = GLGameState.Finished;
	    } else {
		this.state = GLGameState.Paused;
	    }
	    while (true) {
		try {
		    this.stateChanged.wait();
		    break;
		} catch (final InterruptedException e) {
		}
	    }
	}

	this.glView.onPause();
	super.onPause();
    }

    public GLGraphics getGLGraphics() {
	return this.GLGraphics;
    }

    @Override
    public Input getInput() {
	return this.input;
    }

    @Override
    public FileIO getFileIO() {
	return this.fileIO;
    }

    @Override
    public Audio getAudio() {
	return this.audio;
    }

    @Override
    public void setScreen(Screen screen) {

	if (screen == null) {
	    throw new IllegalArgumentException("Screen must not be null");
	}

	this.screen.pause();
	this.screen.dispose();
	screen.resume();
	screen.update(0);
	this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {

	return this.screen;
    }

    public static TypeLogger getlogger() {
	return logger;
    }

    public static void setlogger(TypeLogger logger) {
	GLGame.logger = logger;
    }

    /**
     * 해당 변수는 릴리즈 단계의 어플리케이션이 루아 모드를 사용하는지의 여부를 반환함
     * <p>
     * 액티비티가 생성될 때 판단하며, 그 결과는 어플리케이션이 종료되기 전 까지 변하지 않는 것이 원칙임
     * <p>
     * 
     * @return 유저단계의_모드_지원여부
     */
    public static boolean isPublicModeUsable() {
	return isPublicModeUsable;
    }

}