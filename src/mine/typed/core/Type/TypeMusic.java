
package mine.typed.core.Type;



import java.io.IOException;

import mine.typed.core.interfaces.Music;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;


/**
 * BGM 관련 클래스.
 * 기본적인 기능들을 제공한다.
 * 
 * @author mrminer
 *
 */
public class TypeMusic implements Music , OnCompletionListener {

	MediaPlayer player;
	boolean isPrepared;

	public TypeMusic( final AssetFileDescriptor assetD ) {

		this.player = new MediaPlayer( );

		try {
			this.player.setDataSource( assetD.getFileDescriptor( ) ,
					assetD.getStartOffset( ) , assetD.getLength( ) );
			this.player.prepare( );
			this.isPrepared = true;
			this.player.setOnCompletionListener( this );

		} catch ( final Exception e ) {
			throw new RuntimeException( "Couldn't load music" );
		}

	}

	@Override
	public void play( ) {
		if ( this.player.isPlaying( ) ) {
			return;
		}

		try {
			synchronized ( this ) {
				if ( !this.isPrepared ) {
					this.player.prepare( );
				}
				this.player.start( );
			}

		} catch ( final IllegalStateException e ) {
			e.printStackTrace( );
		} catch ( final IOException e ) {
			e.printStackTrace( );
		}

	}

	@Override
	public void stop( ) {
		this.player.stop( );
		synchronized ( this ) {
			this.isPrepared = false;
		}

	}

	@Override
	public void pause( ) {
		this.player.pause( );
	}

	@Override
	public void setLooping(final boolean looping) {
		this.player.setLooping( looping );
	}

	@Override
	public void setVolume(final float volume1,final float volume2) {
		this.player.setVolume( volume1 , volume2 );
	}

	@Override
	public boolean isPlay( ) {
		return this.player.isPlaying( );
	}

	@Override
	public boolean isStopped( ) {
		return !this.isPrepared;
	}

	@Override
	public boolean isLooping( ) {
		return this.player.isLooping( );
	}

	@Override
	public void dispose( ) {
		if ( this.player.isPlaying( ) ) {
			this.player.stop( );
		}
		this.player.release( );

	}

	@Override
	public void onCompletion(final MediaPlayer mp) {
		synchronized ( this ) {
			this.isPrepared = false;
		}

	}

}
