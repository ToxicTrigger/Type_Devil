
package mine.typed.core;



import android.opengl.Matrix;
import android.util.FloatMath;



public class V3 {
	private static final float[ ] matrix = new float[ 16 ];
	private static final float[ ] inVec = new float[ 4 ];
	private static final float[ ] outVec = new float[ 4 ];
	public float x, y, z;

	public V3( ) {

	}

	public V3( final float x, final float y, final float z ) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public V3( final V3 other ) {

		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}

	public V3 cpy( ) {

		return new V3( this.x , this.y , this.z );
	}

	public V3 set(final float x, final float y, final float z) {

		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public V3 set(final V3 other) {

		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		return this;
	}

	public V3 add(final float x, final float y, final float z) {

		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public V3 add(final V3 other) {

		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
		return this;
	}

	public V3 sub(final float x, final float y, final float z) {

		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public V3 sub(final V3 other) {

		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
		return this;
	}

	public V3 mul(final float scalar) {

		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}

	public float len( ) {

		return FloatMath.sqrt( (this.x * this.x) + (this.y * this.y) + (this.z * this.z) );
	}

	public V3 nor( ) {

		final float len = this.len( );
		if ( len != 0 ) {
			this.x /= len;
			this.y /= len;
			this.z /= len;
		}
		return this;
	}

	public V3 rotate(final float angle, final float axisX, final float axisY, final float axisZ) {

		V3.inVec[ 0 ] = this.x;
		V3.inVec[ 1 ] = this.y;
		V3.inVec[ 2 ] = this.z;
		V3.inVec[ 3 ] = 1;
		Matrix.setIdentityM( V3.matrix , 0 );
		Matrix.rotateM( V3.matrix , 0 , angle , axisX , axisY , axisZ );
		Matrix.multiplyMV( V3.outVec , 0 , V3.matrix , 0 , V3.inVec , 0 );
		this.x = V3.outVec[ 0 ];
		this.y = V3.outVec[ 1 ];
		this.z = V3.outVec[ 2 ];
		return this;
	}

	public float dist(final V3 other) {

		final float distX = this.x - other.x;
		final float distY = this.y - other.y;
		final float distZ = this.z - other.z;
		return FloatMath.sqrt( (distX * distX) + (distY * distY)
				+ (distZ * distZ) );
	}

	public float dist(final float x, final float y, final float z) {

		final float distX = this.x - x;
		final float distY = this.y - y;
		final float distZ = this.z - z;
		return FloatMath.sqrt( (distX * distX) + (distY * distY)
				+ (distZ * distZ) );
	}

	public float distSquared(final V3 other) {

		final float distX = this.x - other.x;
		final float distY = this.y - other.y;
		final float distZ = this.z - other.z;
		return (distX * distX) + (distY * distY) + (distZ * distZ);
	}

	public float distSquared(final float x, final float y, final float z) {

		final float distX = this.x - x;
		final float distY = this.y - y;
		final float distZ = this.z - z;
		return (distX * distX) + (distY * distY) + (distZ * distZ);
	}

	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + Float.floatToIntBits( this.x );
		result = (prime * result) + Float.floatToIntBits( this.y );
		result = (prime * result) + Float.floatToIntBits( this.z );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !(obj instanceof V3) )
			return false;
		final V3 other = (V3) obj;
		if ( Float.floatToIntBits( this.x ) != Float.floatToIntBits( other.x ) )
			return false;
		if ( Float.floatToIntBits( this.y ) != Float.floatToIntBits( other.y ) )
			return false;
		if ( Float.floatToIntBits( this.z ) != Float.floatToIntBits( other.z ) )
			return false;
		return true;
	}
}
