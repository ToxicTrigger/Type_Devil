package mine.typed.core.game;

import mine.typed.core.V3;

/**
 * 움직임이 있는 동적인 3D 객체의 구현체
 * 
 * @author mrminer
 *
 */
public class DynamicGameObject3D extends GameObject {
    public final V3 velocity;
    public final V3 accel;
    public final V3 mcb;

    public static final String TYPE_DYNAMIC_GAME_OBJECT_3D = "<DynamicGameObject3D>";

    /**
     * 3D 의 유동적인 객체를 생성 합니다.
     * 
     * @param x
     * @param y
     * @param z
     * @param radius
     *            충돌체크에 사용될 구의 반지름 값 이다.
     */
    public DynamicGameObject3D(final float x, final float y, final float z,
	    final float radius) {

	super(x, y, z, radius);
	this.velocity = new V3();
	this.accel = new V3();
	this.mcb = null;
    }

    /**
     * 3D 의 유동적인 객체를 생성 합니다.
     * <p>
     * 충돌체크시 원이 아닌 사각형을 사용한다.
     * 
     * @param x
     * @param y
     * @param z
     * @param width
     * @param height
     */
    public DynamicGameObject3D(final float x, final float y, final float z,
	    final float width, final float height) {

	super(x, y, width, height);
	this.velocity = new V3();
	this.accel = new V3();
	this.mcb = new V3();
    }

    @Override
    public String toString() {
	return "DynamicGameObject3D [velocity=" + velocity + ", accel=" + accel + ", mcb=" + mcb + ", position=" + position + ", id=" + id + "]";
    }

}
