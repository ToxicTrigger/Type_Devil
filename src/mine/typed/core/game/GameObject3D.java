
package mine.typed.core.game;

import mine.typed.core.ID;
import mine.typed.core.Rectangle;
import mine.typed.core.Sphere;
import mine.typed.core.V3;



public class GameObject3D {
	public final V3 position;
	public final Sphere bounds;
	public final Rectangle mcb;

	public final ID id;
	
	public static final String TYPE_GAME_OBJECT_3D = "<GameObject3D>";

	/**
	 * 3D 상에서의 오브젝트를 정의한다.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 *            충돌체크
	 */
	public GameObject3D( final float x, final float y, final float z, final float radius ) {

		this.position = new V3( x , y , z );
		this.bounds = new Sphere( x , y , z , radius );
		this.mcb = null;

		id = new ID(this);
	}
	/**
	 * 3D 상에서의 오브젝트를 정의한다.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param cbW
	 *            충돌체크박스
	 * @param cbH
	 *            충돌체크박스
	 */
	public GameObject3D( final float x, final float y, final float z, final float cbW, final float cbH ) {

		this.position = new V3( x , y , z );
		this.bounds = null;
		this.mcb = new Rectangle( x - (cbW / 2) , y - (cbH / 2) , cbW , cbH );

		id = new ID(this);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mcb == null) ? 0 : mcb.hashCode());
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
		GameObject3D other = (GameObject3D) obj;
		if (bounds == null) {
			if (other.bounds != null)
				return false;
		} else if (!bounds.equals(other.bounds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mcb == null) {
			if (other.mcb != null)
				return false;
		} else if (!mcb.equals(other.mcb))
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
		return "GameObject3D [position=" + position + ", bounds=" + bounds
				+ ", mcb=" + mcb + ", id=" + id + "]";
	}


}
