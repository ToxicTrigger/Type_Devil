
package mine.typed.core.Type;



import mine.typed.core.Circle;
import mine.typed.core.OverlapTester;
import mine.typed.core.V2;
import mine.typed.core.interfaces.Sound;
import android.media.SoundPool;
import android.util.Log;



public class TypeSound implements Sound {
	
	int soundID;
	SoundPool pool;
	public final static String TAG = "<TypeSound>";
	private Sound.State state;

	public TypeSound( final SoundPool soundPool, final int SoundID ) {
		this.pool = soundPool;
		this.soundID = SoundID;
		state = Sound.State.Init;
	}
	
	public Sound.State getState(){
		return state;
	}
	
	@Override
	public void setVolume(float l, float r){
		this.pool.setVolume(soundID, l, r);
	}
	
	public static V2 getDistVolume(V2 soundPos, V2 listenerPos, float soundForce, float listenerLength){
		Circle soundfoc, listener;
		soundfoc = new Circle(soundPos, soundForce);
		listener = new Circle(listenerPos, listenerLength);
		
		float sx, lx;
		sx = soundPos.x;
		lx = listenerPos.x;
		V2 vol = new V2();
		
		V2 sL;
		sL = new V2(soundPos.x - soundForce/2, soundPos.y - soundForce/2);
		new V2(soundPos.x + soundForce/2, soundPos.y + soundForce/2);
		new V2(listenerPos.x - listenerLength/2, listenerPos.y - listenerLength/2);
		new V2(listenerPos.x + listenerLength/2, listenerPos.y + listenerLength/2);
		
		if(OverlapTester.overlap(soundfoc, listener)){
			if(sx > lx){
				vol.x = 1.0f -(1.0f - listenerPos.dist(sL) / (listenerLength));
				vol.y = 1.0f - listenerPos.dist(sL) / (listenerLength/2);
			}
		}
		
		return vol;

	}

	@Override
	public void play(final float volumeL ,final float volumeR) {
		this.pool.setVolume( this.soundID , volumeL , volumeR );
		if(state == State.pause)
			this.pool.resume(soundID);
		else
			this.pool.play( this.soundID , volumeL , volumeR , 0 , 0 , 1 );
		
		state = Sound.State.playing;
		Log.i( TypeSound.TAG, "Sound number ' -" + this.soundID + "- ' is Played " );
	}
	
	
	public void stop(){
		this.pool.pause(this.soundID);
		this.state = Sound.State.pause;
	}

	@Override
	public void dispose( ) {
		this.pool.unload( this.soundID );
	}

}
