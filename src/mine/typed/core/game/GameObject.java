
package mine.typed.core.game;

import mine.typed.core.Circle;
import mine.typed.core.ID;
import mine.typed.core.Rectangle;
import mine.typed.core.V2;

/**
 * 게임을 구성하는 객체들 중 움직임이 없는 객체들의 슈퍼 클래스 입니다.
 * @author mrminer
 *
 */
public class GameObject{
	public final V2 position;
	
	public final Rectangle bounds;
	public final Circle cir;

	protected final ID id;

	public static final String TYPE_GAME_OBJECT = "<GameObject>";

	/**
	 * 게임 객체를 생성 합니다. <p>
	 * 충돌 박스를 생성 합니다.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public GameObject( final float x, final float y, final float width, final float height ) {

		this.position = new V2( x , y );
		this.bounds = new Rectangle( x - (width / 2) , y - (height / 2) , width , height );
		this.cir = null;
		
		id = new ID(this);

	}

	/**
	 * 게임 객체를 생성 합니다. <p>
	 * 충돌 박스를 생성 합니다.
	 * @param v
	 * @param width
	 * @param height
	 */
	public GameObject(V2 v, final float width, final float height ) {

		this.position = new V2( v.x , v.y );
		this.bounds = new Rectangle( v.x - (width / 2) , v.y - (height / 2) , width , height );
		this.cir = null;

		id = new ID(this);

	}

	/**
	 * 게임 객체를 생성 합니다. <p>
	 * 충돌 원을 생성 합니다.
	 * @param x
	 * @param y
	 * @param r 반지름 
	 */
	public GameObject( final float x, final float y, final float r ) {

		this.position = new V2( x , y );
		this.cir = new Circle( x , y , r );
		this.bounds = null;
		

		id = new ID(this);
	}

	/**
	 * 게임 객체를 생성 합니다<p>
	 * 충돌 원을 생성 합니다.
	 * @param v
	 * @param r
	 */
	public GameObject( V2 v , final float r ) {
		this.position = new V2( v.x , v.y );
		this.cir = new Circle( v.x , v.y , r );
		this.bounds = null;

		id = new ID(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
		result = prime * result + ((cir == null) ? 0 : cir.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameObject other = (GameObject) obj;
		if (bounds == null) {
			if (other.bounds != null)
				return false;
		} else if (!bounds.equals(other.bounds))
			return false;
		if (cir == null) {
			if (other.cir != null)
				return false;
		} else if (!cir.equals(other.cir))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameObject [position=" + position + ", bounds=" + bounds
				+ ", cir=" + cir + ", id=" + id + "]";
	}

	public double getIDcode() {
		return id.getIDcode();
	}



}
