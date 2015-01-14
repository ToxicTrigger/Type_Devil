
package mine.typed.core;


/**
 * 구를 정의 한 클래스 입니다.
 * @author mrminer
 *
 */
public class Sphere {
	public final V3 center = new V3( );
	public float radius;

	/**
	 * 3D 구 를 생성 합니다.
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 */
	public Sphere( final float x, final float y, final float z, final float radius ) {

		this.center.set( x , y , z );
		this.radius = radius;
	}

	public Sphere(final V3 v3 , final float radius){
		this.center.set( v3 );
		this.radius = radius;
	}

	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.center == null) ? 0 : this.center.hashCode( ));
		result = (prime * result) + Float.floatToIntBits( this.radius );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !(obj instanceof Sphere) )
			return false;
		final Sphere other = (Sphere) obj;
		if ( this.center == null ) {
			if ( other.center != null )
				return false;
		} else if ( !this.center.equals( other.center ) )
			return false;
		if ( Float.floatToIntBits( this.radius ) != Float.floatToIntBits( other.radius ) )
			return false;
		return true;
	}
}
