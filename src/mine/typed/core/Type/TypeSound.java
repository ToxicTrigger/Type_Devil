
package mine.typed.core.Type;



import mine.typed.core.interfaces.Sound;
import android.media.SoundPool;
import android.util.Log;



public class TypeSound implements Sound {
	int soundID;
	SoundPool pool;
	public final static String TAG = "<TypeSound>";

	public TypeSound( final SoundPool soundPool, final int SoundID ) {

		this.pool = soundPool;
		this.soundID = SoundID;
	}

	@Override
	public void setVolume(float L , float R){
		this.pool.setVolume( this.soundID , L , R );
	}

	@Override
	public void play(final float volumeL ,final float volumeR) {

		this.pool.setVolume( this.soundID , volumeL , volumeR );
		this.pool.play( this.soundID , volumeL , volumeR , 0 , 0 , 1 );
		Log.i( TypeSound.TAG, "Sound number ' -" + this.soundID + "- ' is Played " );
	}

	@Override
	public void dispose( ) {
		this.pool.unload( this.soundID );
	}

}
