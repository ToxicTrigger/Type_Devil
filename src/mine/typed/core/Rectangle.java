package mine.typed.core;

/**
 * 사각형을 정의한 클래스 입니다.
 * 
 * @author mrminer
 *
 */
public class Rectangle extends Diagram {
    public final V2 lowerLeft;
    public float width, height;

    /**
     * 사각형을 생성 합니다.
     * 
     * @param 좌측
     *            하단 꼭지점 X 위치
     * @param 좌측
     *            하단 꼭지점 Y 위치
     * @param 가로
     *            길이
     * @param 세로
     *            길이
     */
    public Rectangle(final float x, final float y, final float width,
	    final float height) {

	this.lowerLeft = new V2(x, y);
	this.width = width;
	this.height = height;
    }

    /**
     * 사각형을 생성 합니다.
     * 
     * @param 좌측
     *            하단 꼭지점 좌표
     * @param width
     * @param height
     */
    public Rectangle(V2 v, final float width, final float height) {

	this.lowerLeft = new V2(v.x, v.y);
	this.width = width;
	this.height = height;
    }

    @Override
    public int hashCode() {

	final int prime = 31;
	int result = 1;
	result = (prime * result) + Float.floatToIntBits(this.height);
	result = (prime * result) + ((this.lowerLeft == null) ? 0 : this.lowerLeft
		.hashCode());
	result = (prime * result) + Float.floatToIntBits(this.width);
	return result;
    }

    @Override
    public boolean equals(Object obj) {

	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Rectangle))
	    return false;
	final Rectangle other = (Rectangle) obj;
	if (Float.floatToIntBits(this.height) != Float
		.floatToIntBits(other.height))
	    return false;
	if (this.lowerLeft == null) {
	    if (other.lowerLeft != null)
		return false;
	} else if (!this.lowerLeft.equals(other.lowerLeft))
	    return false;
	if (Float.floatToIntBits(this.width) != Float
		.floatToIntBits(other.width))
	    return false;
	return true;
    }
}
