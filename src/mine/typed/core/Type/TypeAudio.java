package mine.typed.core.Type;

import java.io.IOException;

import mine.typed.core.interfaces.Audio;
import mine.typed.core.interfaces.Music;
import mine.typed.core.interfaces.Sound;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 음향 관련 객체를 생성해준다.
 * 
 * @author mrminer
 *
 */
public class TypeAudio implements Audio {

    AssetManager assent;
    SoundPool pool;

    @SuppressWarnings("deprecation")
    public TypeAudio(final Activity activity) {

	// 시스템에서 설정된 미디어 볼륨 값을 기본으로 한다.
	activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	this.assent = activity.getAssets();
	this.pool = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(final String filename) {
	try {
	    final AssetFileDescriptor assetD = this.assent.openFd(filename);
	    return new TypeMusic(assetD);
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't load music '" + filename + "' ");
	}
    }

    @Override
    public Sound newSound(final String filename) {
	try {
	    final AssetFileDescriptor assetD = this.assent.openFd(filename);
	    final int soundID = this.pool.load(assetD, 0);
	    return new TypeSound(this.pool, soundID);
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't load Sound '" + filename + "' ");
	}
    }

}
