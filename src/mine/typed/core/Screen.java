package mine.typed.core;

import mine.typed.core.interfaces.Game;

public abstract class Screen
{
	protected final Game game;

	public Screen(final Game game)
	{

		this.game = game;
	}

	/**
	 * 주기적으로 업데이트 될 것 들을 적습니다.
	 * 
	 * @param deltaTime
	 */
	public abstract void update(float deltaTime);

	public abstract void lateUpdate(float deltaTime);

	public abstract void resent(float deltaTime);

	/**
	 * 화면에 그릴 것 들을 적습니다.
	 * 
	 * @param deltaTime
	 */
	public abstract void present(float deltaTime);

	public abstract void drawBackBuffer(float deltaTime);

	public abstract void drawUpBuffer(float deltaTime);

	/**
	 * 일시정지 이벤트가 발생할 경우 처리할 내용을 기술합니다.
	 */
	public abstract void pause();

	/**
	 * 스크린 복귀시 할 행동
	 */
	public abstract void resume();

	/**
	 * 스크린이 제거 될때 행동
	 */
	public abstract void dispose();

	/**
	 * {@code update()} 에서 호출하세요
	 */
	public abstract void updateTouch();

}
