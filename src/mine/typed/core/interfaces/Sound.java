
package mine.typed.core.interfaces;



public interface Sound {

	/**
	 * 소리를 재생합니다.
	 * @param volumeL
	 * @param volumeR
	 */
	public void play(float volumeL , float volumeR);
	/**
	 * 볼륨을 설정 합니다.
	 * @param L
	 * @param R
	 */
	public void setVolume(float L , float R);
	/**
	 * 해제합니다.
	 */
	public void dispose( );

}
