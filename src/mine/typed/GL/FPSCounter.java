package mine.typed.GL;

import android.util.Log;

/**
 * 초당 화면에 몇번 그려지는지를 확인하게 해준다.
 * 
 * @author mrminer
 *
 */
public class FPSCounter
{
	long startTime = System.nanoTime();
	int frames = 0;

	/**
	 * 로그를 통해 그려지는 횟수를 출력 해줍니다.
	 */
	public void logFrame()
	{

		this.frames++;
		if( (System.nanoTime() - this.startTime) >= 1000000000 )
		{
			Log.d("FPSCounter", "fps: " + this.frames);
			this.frames = 0;
			this.startTime = System.nanoTime();
		}
	}

	/**
	 * 1초 마다 0 이상의 수를 반환한다.
	 * 
	 * @return -999 는 1초 전 프레임임 무시.
	 */
	public int getFrame()
	{
		this.frames++;
		int tmp = this.frames;
		if( (System.nanoTime() - this.startTime) >= 1000000000 )
		{
			// Log.d( "FPSCounter" , "fps: " + frames );
			tmp = this.frames;
			this.frames = 0;
			this.startTime = System.nanoTime();
			return tmp;
		} else
		{
			return -999;
		}

	}
}
