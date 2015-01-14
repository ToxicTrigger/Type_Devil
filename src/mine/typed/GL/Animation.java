
package mine.typed.GL;

import android.content.res.Resources.NotFoundException;


/**
 * 스프라이트 애니메이션을 구현했습니다.
 * @author mrminer
 *
 */
public class Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;
	
	public static final int NULL = 99999;

	private final TextureRegion[ ] keyFrames;
	
	public TextureRegion[] getKeyFrames(){
		return this.keyFrames;
	}
	
	public boolean isThatFrame(TextureRegion frame){
		int len = this.keyFrames.length;
		for(int i = 0 ; i < len ; i++){
			TextureRegion src = this.keyFrames[i];
			if(frame.equals(src)) return true;
		}
		return false;
	}
	
	private int getThatFramesNumber(TextureRegion frame) throws NotFoundException{
		int len = this.keyFrames.length;
		for(int i = 0 ; i < len ; i++){
			TextureRegion src = this.keyFrames[i];
			if(frame.equals(src)) return i;
		}
		throw new NotFoundException();
	}
	
	public int getFramesNumber(TextureRegion frame){
		int num = NULL;
		try{
			num = this.getThatFramesNumber(frame);
		} catch(NotFoundException e){
			
		}

		return num;
	}
	
	private final float frameDuration;
	
	public float getFrameDuration(){
		return this.frameDuration;
	}

	/**
	 * 애니메이션을 생성 합니다.
	 * 
	 * @param frameDuration
	 *            프레임이 몇초간 지속될지 결정하는 값
	 * @param keyFrames
	 *            미리 정의된 '혹은 그렇지 않은' TextuerRegion 을 배열에 넣는다. 이 클래스가 동작하는 방법은
	 *            선언후 , SpriteBatcher.Draw 를 호출할때 Region 값을 넣는 곳에 getKeyFrame()
	 *            을 넣어주면 된다.
	 */
	public Animation( final float frameDuration, final TextureRegion... keyFrames ) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
	}

	/**
	 * 
	 * @param stateTime
	 * @param mode
	 *            ANIMATION_NONLOOPING , ANIMATION_LOOPING 중 택1
	 * @return Duration 값과 mode 의 값을 이용하여 TR 배열의 커서를 반환한다.
	 */
	public TextureRegion getKeyFrame(final float stateTime, final int mode) {

		int frameNumber = (int) (stateTime / this.frameDuration);

		if ( mode == Animation.ANIMATION_NONLOOPING ) {
			frameNumber = Math.min( this.keyFrames.length - 1 , frameNumber );
		} else {
			frameNumber = frameNumber % this.keyFrames.length;
		}
		return this.keyFrames[ frameNumber ];
	}
	
}
