package mine.typed.GL;

/**
 * 스프라이트 애니메이션을 구현했습니다.
 * 
 * @author mrminer
 *
 */
public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;

    public static final int NULL = 99999;

    private final TextureRegion[] keyFrames;
    private final float frameDuration;

    /**
     * 애니메이션을 생성 합니다.
     * 
     * @param frameDuration
     *            프레임이 몇초간 지속될지 결정하는 값
     * @param keyFrames
     *            미리 정의된 '혹은 그렇지 않은' {@link TextureRegion} 을 배열에 넣는다. 이 클래스가
     *            동작하는 방법은 선언후 , {@link SpriteBatcher}.Draw 를 호출할때
     *            {@link TextureRegion} 값을 넣는 곳에 getKeyFrame() 을 넣어주면 된다.
     */
    public Animation(final float frameDuration,
	    final TextureRegion... keyFrames) {
	this.frameDuration = frameDuration;
	this.keyFrames = keyFrames;
    }

    public TextureRegion[] getKeyFrames() {
	return this.keyFrames;
    }

    /**
     * @param frame
     * @return 프레임이 해당 매서드를 호출하는 애니메이션 클래스에 들어있는지에 대한 여부
     */
    public boolean isThatFrameInThisAnimation(TextureRegion frame) {
	int len = this.keyFrames.length;
	for (int i = 0; i < len; i++) {
	    TextureRegion src = this.keyFrames[i];
	    if (frame.equals(src))
		return true;
	}
	return false;
    }

    /**
     * 
     * @param frame
     * @return 배열 속 해당 프레임의 위치
     * @throws NullPointerException
     */
    private int getThatFramesNumber(TextureRegion frame)
	    throws NullPointerException {
	int len = this.keyFrames.length;
	for (int i = 0; i < len; i++) {
	    TextureRegion src = this.keyFrames[i];
	    if (frame.equals(src))
		return i;
	}
	throw new NullPointerException();
    }

    /**
     * <code> getThatFramesNumber(TextureRegion) </code> 의 예외를 캡슐화한 함수
     * 
     * @param frame
     * @return 배열 속 해당 프레임의 위치
     */
    public int getFramesNumber(TextureRegion frame) {
	int num = NULL;
	try {
	    num = this.getThatFramesNumber(frame);
	} catch (NullPointerException e) {
	    System.err.println("I can't Found that frame on this Animation.");
	}
	return num;
    }

    /**
     * 
     * @param frame
     * @param stateTime
     * @param mode
     * @return 지금 해당 프레임이 지금 그려지려 하는지에 대한 여부
     */
    public boolean isThatFrameOnView(TextureRegion frame, float stateTime,
	    final int mode) {
	if (!this.isThatFrameInThisAnimation(frame))
	    return false;
	if (this.getKeyFrame(stateTime, mode).equalHashCode(frame))
	    return true;
	return false;
    }

    public float getFrameDuration() {
	return this.frameDuration;
    }

    /**
     * 
     * @param stateTime
     * @param mode
     *            ANIMATION_NONLOOPING | ANIMATION_LOOPING
     * @return Duration 값과 mode 의 값을 이용하여 {@link TextureRegion} 배열의 커서를 반환
     */
    public TextureRegion getKeyFrame(float stateTime, final int mode) {

	int frameNumber = (int) (stateTime / this.frameDuration);

	if (mode == Animation.ANIMATION_NONLOOPING) {
	    frameNumber = Math.min(this.keyFrames.length - 1, frameNumber);
	} else {
	    frameNumber = frameNumber % this.keyFrames.length;
	}
	return this.keyFrames[frameNumber];
    }

}
