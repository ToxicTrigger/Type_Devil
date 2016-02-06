package mine.typed.core.game;

import java.util.ArrayList;

import mine.typed.core.Circle;
import mine.typed.core.ID;
import mine.typed.core.Rectangle;
import mine.typed.core.V2;

/**
 * 게임을 구성하는 객체들 중 움직임이 없는 객체들의 슈퍼 클래스 입니다.
 * 
 * @author mrminer
 *
 */
public class GameObject {
    public enum State {
	Idle,
	Wake,
	Dead,
	Sleep
    }
    
    protected ArrayList<GameObject> descendants;

    public V2 position;

    public Rectangle bounds;
    public Circle cir;
    
    private GameObject.State state;

    protected final ID id;

    public static final int L = 0, R = 1;
    public int look;
    
    public float angle;

    public static final String TYPE_GAME_OBJECT = "<GameObject>";

    /**
     * 게임 객체를 생성 합니다.
     * <p>
     * 충돌 박스를 생성 합니다.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public GameObject(final float x, final float y, final float width, final float height) {
	this.position = new V2(x, y);
	this.bounds = new Rectangle(x - (width / 2), y - (height / 2), width, height);
	this.cir = null;
	
	this.descendants = new  ArrayList<GameObject>();

	id = new ID(this);
	state = State.Idle;
    }


    /**
     * 게임 객체를 생성 합니다.
     * <p>
     * 충돌 박스를 생성 합니다.
     * 
     * @param v
     * @param width
     * @param height
     */
    public GameObject(V2 v, final float width, final float height) {
	this.position = new V2(v.x, v.y);
	this.bounds = new Rectangle(v.x - (width / 2), v.y - (height / 2), width, height);
	this.cir = new Circle(position, width);
	this.descendants = new  ArrayList<GameObject>();
	id = new ID(this);
	state = State.Idle;
    }

    /**
     * 게임 객체를 생성 합니다.
     * <p>
     * 충돌 원을 생성 합니다.
     * 
     * @param x
     * @param y
     * @param r
     *            반지름
     */
    public GameObject(final float x, final float y, final float r) {
	this.position = new V2(x, y);
	this.cir = new Circle(x, y, r);
	this.bounds = new Rectangle(x - (r / 2), y - (r / 2), r, r);
	this.descendants = new  ArrayList<GameObject>();
	id = new ID(this);
	state = State.Idle;
    }

    /**
     * 게임 객체를 생성 합니다
     * <p>
     * 충돌 원을 생성 합니다.
     * 
     * @param v
     * @param r
     */
    public GameObject(V2 v, final float r) {
	this.position = new V2(v.x, v.y);
	this.cir = new Circle(v.x, v.y, r);
	this.bounds = new Rectangle(v.x - (r / 2), v.y - (r / 2), r, r);
	this.descendants = new  ArrayList<GameObject>();
	id = new ID(this);
	state = State.Idle;
    }
    
    public ArrayList<GameObject> getDescentants(){
	return this.descendants;
    }
    
    public void addDescendants(GameObject des, V2 offset){
	des.position.set(offset);
	this.descendants.add(des);
    }
    
    public GameObject removeDescendants(int index){
	return this.descendants.remove(index);
    }
    
    public boolean removeDescendants(GameObject object){
	return this.descendants.remove(object);
    }
    
    public V2 getPosition(){
	return this.position;
    }
    
    public State getState(){
	return this.state;
    }
    
    public void setState(GameObject.State state){
	this.state = state;
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Float.floatToIntBits(angle);
	result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
	result = prime * result + ((cir == null) ? 0 : cir.hashCode());
	result = prime * result + ((descendants == null) ? 0 : descendants
		.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + look;
	result = prime * result + ((position == null) ? 0 : position.hashCode());
	result = prime * result + ((state == null) ? 0 : state.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof GameObject)) {
	    return false;
	}
	GameObject other = (GameObject) obj;
	if (Float.floatToIntBits(angle) != Float.floatToIntBits(other.angle)) {
	    return false;
	}
	if (bounds == null) {
	    if (other.bounds != null) {
		return false;
	    }
	} else if (!bounds.equals(other.bounds)) {
	    return false;
	}
	if (cir == null) {
	    if (other.cir != null) {
		return false;
	    }
	} else if (!cir.equals(other.cir)) {
	    return false;
	}
	if (descendants == null) {
	    if (other.descendants != null) {
		return false;
	    }
	} else if (!descendants.equals(other.descendants)) {
	    return false;
	}
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	if (look != other.look) {
	    return false;
	}
	if (position == null) {
	    if (other.position != null) {
		return false;
	    }
	} else if (!position.equals(other.position)) {
	    return false;
	}
	if (state != other.state) {
	    return false;
	}
	return true;
    }


    public double getIDcode() {
	return id.getIDcode();
    }

}
