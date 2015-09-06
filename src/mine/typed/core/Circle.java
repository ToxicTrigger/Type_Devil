package mine.typed.core;

/**
 * 원을 정의한 클래스
 * 
 * @author mrminer
 * 
 */
public class Circle extends Diagram
{
	public final V2 center = new V2();
	public float radius;

	/**
	 * 원을 정의한 클래스
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 */
	public Circle(final float x, final float y, final float radius)
	{

		this.center.set(x, y);
		this.radius = radius;
	}

	/**
	 * 원을 정의한 클래스
	 * 
	 * @param v
	 * @param radius
	 */
	public Circle(V2 v, final float radius)
	{

		this.center.set(v.x, v.y);
		this.radius = radius;
	}

	@Override
	public int hashCode()
	{

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.center == null) ? 0 : this.center.hashCode());
		result = (prime * result) + Float.floatToIntBits(this.radius);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{

		if( this == obj ) return true;
		if( obj == null ) return false;
		if( !(obj instanceof Circle) ) return false;
		final Circle other = (Circle) obj;
		if( this.center == null )
		{
			if( other.center != null ) return false;
		} else if( !this.center.equals(other.center) ) return false;
		if( Float.floatToIntBits(this.radius) != Float.floatToIntBits(other.radius) ) return false;
		return true;
	}
}
