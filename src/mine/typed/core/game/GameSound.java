package mine.typed.core.game;

import mine.typed.core.V2;
import mine.typed.core.interfaces.Music;
import mine.typed.core.interfaces.Sound;

/**
 * 게임속 사운드의 구현체 입니다. 3D 입체적인 사운딩 효과를 구현할수 있도록 해줍니다.
 * 
 * @author mrminer
 *
 */
public class GameSound extends DynamicGameObject {

    public Sound sound;
    public Music music;

    public float l, r;

    /**
     * 사운드는 하나의 점으로 판단합니다.
     * 
     * @param x
     * @param y
     * @param r
     * @param sound
     *            이 객체가 가질 소리 입니다.
     */
    public GameSound(float x, float y, float r, Sound sound) {
	super(x, y, r);
	this.sound = sound;
    }

    /**
     * 사운드는 하나의 점으로 판단합니다.
     * 
     * @param x
     * @param y
     * @param r
     * @param music
     *            이 객체가 가질 음악 입니다.
     */
    public GameSound(float x, float y, float r, Music music) {
	super(x, y, r);
	this.music = music;
    }

    public void playSound() {

    }

    public void playMusic(boolean loop) {

    }

    /**
     * 3D 볼륨을 구현화 한 코드 입니다.
     * <p>
     * <code> update() <code> 에 포함시켜서 수시로 검사하게 하세요.
     * 
     * @param 거리를
     *            잴 객체
     * @param 화면의
     *            가로 길이
     */
    public void updateVolum(V2 objPos, float ScreenW) {

	if (this.sound != null) {
	    // 화면 밖에 있다고 가정 했을때.
	    if (this.getPosition().dist(objPos) > ScreenW) {
		l = 0;
		r = 0;
		this.sound.setVolume(l, r);
	    } else {

		final float x = objPos.x - this.getPosition().x;
		if (x > 0) {
		    l = 1.0f - (objPos.dist(this.getPosition()) / ScreenW);
		    r = 1.0f - (objPos.dist(this.getPosition()) / (ScreenW / 2));
		} else {
		    l = 1.0f - (objPos.dist(this.getPosition()) / (ScreenW / 2));
		    r = 1.0f - (objPos.dist(this.getPosition()) / ScreenW);
		}
		((Music) this.sound).setVolume(l, r);

	    }
	} else {
	    // 화면 밖에 있다고 가정 했을때.
	    if (this.getPosition().dist(objPos) > ScreenW) {
		l = 0;
		r = 0;
		this.music.setVolume(l, r);
	    } else {
		final float x = objPos.x - this.getPosition().x;
		if (x > 0) {
		    l = 1.0f - (objPos.dist(this.getPosition()) / ScreenW);
		    r = 1.0f - (objPos.dist(this.getPosition()) / (ScreenW / 2));
		} else {
		    l = 1.0f - (objPos.dist(this.getPosition()) / (ScreenW / 2));
		    r = 1.0f - (objPos.dist(this.getPosition()) / ScreenW);
		}
		this.music.setVolume(l, r);
	    }
	}
    }

}
