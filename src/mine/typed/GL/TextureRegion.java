
package mine.typed.GL;


/**
 * 텍스쳐에서 어떤 영역을 구분할때 쓰입니다.
 * @author mrminer
 *
 */
public class TextureRegion {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		result = prime * result + Float.floatToIntBits(u1);
		result = prime * result + Float.floatToIntBits(u2);
		result = prime * result + Float.floatToIntBits(v1);
		result = prime * result + Float.floatToIntBits(v2);
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
		TextureRegion other = (TextureRegion) obj;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		if (Float.floatToIntBits(u1) != Float.floatToIntBits(other.u1))
			return false;
		if (Float.floatToIntBits(u2) != Float.floatToIntBits(other.u2))
			return false;
		if (Float.floatToIntBits(v1) != Float.floatToIntBits(other.v1))
			return false;
		if (Float.floatToIntBits(v2) != Float.floatToIntBits(other.v2))
			return false;
		return true;
	}
	
	public boolean equalHashCode(TextureRegion tr){
		int srcHashCode = this.hashCode();
		int dstHashCode = tr.hashCode();
		
		if(srcHashCode == dstHashCode) return true;
		return false;
	}

	public final float u1, v1;
	public final float u2, v2;
	public final Texture texture;

	/**
	 * texture 에서 x , y 를 사각형 좌측 상단 모서리로 정하고 width 와 height 값 만큼 우측과 하단 방향으로 사각형을 정의 합니다.
	 * @param texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public TextureRegion( final Texture texture, final float x, final float y, final float width,
			final float height ) {

		this.u1 = x / texture.width;
		this.v1 = y / texture.height;
		this.u2 = this.u1 + (width / texture.width);
		this.v2 = this.v1 + (height / texture.height);
		this.texture = texture;
	}
}
