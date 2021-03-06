package mine.typed.core.game;

import mine.typed.GL.GLGame;
import mine.typed.core.V2;

public class DynamicGameObject extends GameObject {
    public  V2 velocity;
    public  V2 accel;

    public HitBox hitbox;

    public static final String TYPE_DYNAMIC_GAME_OBJECT = "<DynamicGameObject>";

    /**
     * 유동적인 오브젝트를 생성 합니다.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public DynamicGameObject(final float x, final float y, final float width,
	    final float height) {
	super(x, y, width, height);
	this.velocity = new V2();
	this.accel = new V2();
	this.hitbox = new HitBox(x - (width / 2), y - (height / 2), width, height);

    }

    /**
     * 유동적인 오브젝트를 생성 합니다.
     * 
     * @param v
     * @param width
     * @param height
     */
    public DynamicGameObject(V2 v, final float width, final float height) {

	super(v, width, height);
	this.velocity = new V2();
	this.accel = new V2();
	this.hitbox = new HitBox(v.x - (width / 2), v.y - (height / 2), width, height);

    }

    /**
     * 충돌 박스가 좌표에 맞도록 수시로 업데이트 합니다.
     */
    public void updateHitBox(){

	    this.cir.center.set(position);

	    this.hitbox.lowerLeft.set(this.getPosition().x - (this.bounds.width / 2), this.getPosition().y - (this.bounds.height / 2));
	
    }
    
    public void move(int V2UpStopDown, float delta, float accel, V2 pos, int axis){
	Transfer.move(V2UpStopDown, delta, accel, position, axis);
	
	for(GameObject tmp : this.descendants){
	    Transfer.move(V2UpStopDown, delta, accel, tmp.position, axis);
	}
    }
    
    /**
     * 충돌 체크가 원 인 객체를 생성 합니다.
     * 
     * @param x
     * @param y
     * @param r
     */
    public DynamicGameObject(final float x, final float y, final float r) {
	super(x, y, r);
	this.velocity = new V2();
	this.accel = new V2();
    }

    /**
     * 충돌 체크가 원 인 객체를 생성 합니다.
     * 
     * @param v
     * @param r
     */
    public DynamicGameObject(V2 v, final float r) {

	super(v, r);
	this.velocity = new V2();
	this.accel = new V2();
    }

    @Override
    public String toString() {
	return "DynamicGameObject [velocity=" + velocity + ", accel=" + accel + ", hitbox=" + hitbox + ", position=" + getPosition() + ", id=" + id + "]";
    }

}
