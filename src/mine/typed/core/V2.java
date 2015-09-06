package mine.typed.core;

import android.util.FloatMath;

/**
 * x 와 y 값을 가지는 벡터의 구현체 입니다.
 * 
 * @author mrminer
 *
 */
@SuppressWarnings("deprecation")
public class V2
{
	/**
	 * <code> TO_RADIANS = (1 / 180.0f) * (float) Math.PI </code>
	 */
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;

	public static final int Up = 1;
	public static final int Stop = 0;
	public static final int Down = -1;

	/**
	 * <code> TO_DEGREES = (1 / (float) Math.PI) * 180 </code>
	 */
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	public float x, y;

	public V2()
	{

	}

	/**
	 * 벡터를 생성 합니다.
	 * 
	 * @param x
	 * @param y
	 */
	public V2(final float x, final float y)
	{
		this.x = x;
		this.y = y;
	}

	public float randomRange(float n1, float n2)
	{
		return (float) (Math.random() * (n2 - n1 + 1)) + n1;
	}

	/**
	 * 다른 벡터의 x , y 를 이용하여 새로운 벡터를 생성 합니다.
	 * 
	 * @param other
	 */
	public V2(final V2 other)
	{

		this.x = other.x;
		this.y = other.y;
	}

	/**
	 * 복사
	 * 
	 * @return 해당 벡터의 x , y 값을 가진 "새로운" 벡터
	 */
	public V2 cpy()
	{

		return new V2(this.x, this.y);
	}

	/**
	 * 해당 벡터의 x , y 값을 설정 합니다.
	 * 
	 * @param x
	 * @param y
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 set(final float x, final float y)
	{

		this.x = x;
		this.y = y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 값을 다른 벡터의 x , y 로 설정 합니다.
	 * 
	 * @param other
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 set(final V2 other)
	{

		this.x = other.x;
		this.y = other.y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 에 인자값을 더합니다.
	 * 
	 * @param x
	 * @param y
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 add(final float x, final float y)
	{

		this.x += x;
		this.y += y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 에 다른 벡터의 x , y 를 더합니다.
	 * 
	 * @param other
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 add(final V2 other)
	{

		this.x += other.x;
		this.y += other.y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 에 인자값 만큼 뺍니다.
	 * 
	 * @param x
	 * @param y
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 sub(final float x, final float y)
	{

		this.x -= x;
		this.y -= y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 에 다른 벡터의 x , y 만큼 뺍니다.
	 * 
	 * @param other
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 sub(final V2 other)
	{

		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	/**
	 * 해당 벡터의 x , y 에 인자값을 곱하고 더합니다.
	 * 
	 * @param scalar
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 mul(final float scalar)
	{

		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

	/**
	 * 
	 * @return <code> sqrt( (this.x * this.x) + (this.y * this.y) ) </code>
	 */
	public float len()
	{

		return FloatMath.sqrt((this.x * this.x) + (this.y * this.y));
	}

	/**
	 * 
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 nor()
	{

		final float len = this.len();
		if( len != 0 )
		{
			this.x /= len;
			this.y /= len;
		}
		return this;
	}

	/**
	 * <code> Math.atan2( this.y , this.x ) * V2.TO_DEGREES </code>
	 * 
	 * @return
	 */
	public float angle()
	{

		float angle = (float) Math.atan2(this.y, this.x) * V2.TO_DEGREES;
		if( angle < 0 )
		{
			angle += 360;
		}
		return angle;
	}

	/**
	 * 회전 합니다.
	 * 
	 * @param angle
	 * @return 연산이 끝난 해당 벡터
	 */
	public V2 rotate(final float angle)
	{

		final float rad = angle * V2.TO_RADIANS;
		final float cos = FloatMath.cos(rad);
		final float sin = FloatMath.sin(rad);

		final float newX = (this.x * cos) - (this.y * sin);
		final float newY = (this.x * sin) + (this.y * cos);

		this.x = newX;
		this.y = newY;

		return this;
	}

	/**
	 * 해당 벡터와 다른 벡터의 직선 거리를 구합니다.
	 * 
	 * @param other
	 * @return 거리
	 */
	public float dist(final V2 other)
	{

		final float distX = this.x - other.x;
		final float distY = this.y - other.y;
		return FloatMath.sqrt((distX * distX) + (distY * distY));
	}

	/**
	 * 해당 벡터와 인자값 위치의 점과의 직선 거리를 구합니다.
	 * 
	 * @param x
	 * @param y
	 * @return 거리
	 */
	public float dist(final float x, final float y)
	{

		final float distX = this.x - x;
		final float distY = this.y - y;
		return FloatMath.sqrt((distX * distX) + (distY * distY));
	}

	public float distSquared(final V2 other)
	{

		final float distX = this.x - other.x;
		final float distY = this.y - other.y;
		return (distX * distX) + (distY * distY);
	}

	public float distSquared(final float x, final float y)
	{

		final float distX = this.x - x;
		final float distY = this.y - y;
		return (distX * distX) + (distY * distY);
	}

	public static float quadrantRadian(final float x, final float y, final float angle)
	{

		float ran;

		if( x >= 0 )
		{
			if( y > 0 )
			{
				ran = (float) Math.atan(y / x);
			} else
			{
				ran = (float) Math.atan(y / x);
				ran = (float) ((float) Math.atan(y / x) + (2 * Math.PI));
			}
		} else
		{
			ran = (float) (Math.atan(y / x) + Math.PI);
			if( angle == 1 )
			{
				ran *= 180.0 / Math.PI;
			}
			return ran;
		}
		return ran;
	}

	public float getNormalLine(final float sx, final float sy, final float ex, final float ey, final float angle)
	{

		float dx, dy, tangent;

		dx = ex - sx;
		dy = sy - sy;

		// 두점의 기울기값
		tangent = V2.quadrantRadian(dx, dy, angle);

		if( angle == 1 )
		{
			tangent -= 90;
		} else
		{
			tangent -= 1.570796;
		}
		return tangent;

	}

	@Override
	public int hashCode()
	{

		final int prime = 31;
		int result = 1;
		result = (prime * result) + Float.floatToIntBits(this.x);
		result = (prime * result) + Float.floatToIntBits(this.y);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{

		if( this == obj ) return true;
		if( obj == null ) return false;
		if( !(obj instanceof V2) ) return false;
		final V2 other = (V2) obj;
		if( Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x) ) return false;
		if( Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y) ) return false;
		return true;
	}

}