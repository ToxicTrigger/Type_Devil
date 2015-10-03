package mine.typed.core;

public class Box {

    @Override
    public int hashCode() {

	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((this.LeftEdgeUp == null) ? 0 : this.LeftEdgeUp
		.hashCode());
	result = (prime * result) + ((this.ReightEdgeDown == null) ? 0 : this.ReightEdgeDown
		.hashCode());
	result = (prime * result) + ((this.center == null) ? 0 : this.center
		.hashCode());
	result = (prime * result) + Float.floatToIntBits(this.height);
	result = (prime * result) + Float.floatToIntBits(this.width);
	return result;
    }

    @Override
    public boolean equals(Object obj) {

	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Box))
	    return false;
	final Box other = (Box) obj;
	if (this.LeftEdgeUp == null) {
	    if (other.LeftEdgeUp != null)
		return false;
	} else if (!this.LeftEdgeUp.equals(other.LeftEdgeUp))
	    return false;
	if (this.ReightEdgeDown == null) {
	    if (other.ReightEdgeDown != null)
		return false;
	} else if (!this.ReightEdgeDown.equals(other.ReightEdgeDown))
	    return false;
	if (this.center == null) {
	    if (other.center != null)
		return false;
	} else if (!this.center.equals(other.center))
	    return false;
	if (Float.floatToIntBits(this.height) != Float
		.floatToIntBits(other.height))
	    return false;
	if (Float.floatToIntBits(this.width) != Float
		.floatToIntBits(other.width))
	    return false;
	return true;
    }

    public final V3 LeftEdgeUp = new V3();
    public final V3 ReightEdgeDown = new V3();
    public final V3 center = new V3();
    public final float width, height;

    public Box(float x, float y, float z, float w, float h, float depth) {
	this.center.set(w / 2, y / 2, depth / 2);
	this.LeftEdgeUp.set(x, y, z);
	this.ReightEdgeDown.set(x + w, y - h, z - depth);
	this.width = w;
	this.height = h;
    }

    public V3 center() {
	return this.center;
    }

}
