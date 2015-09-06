
package mine.typed.GL;



import javax.microedition.khronos.opengles.GL10;

import mine.typed.core.V2;
import android.util.FloatMath;

/**
 * 객체를 화면에 배치해주는 클래스입니다.<p>
 * 한 객체를 그리고 다른 객체를 그리는 형식이 아닌, 한개의 폴리곤에 그릴 것들을 몰아서 그립니다.<p>
 * 효과적이고 빠르게 메모리를 절약할수 있습니다.
 * @author mrminer
 *
 */
@SuppressWarnings("deprecation")
public class SpriteBatcher {
	final float[ ] verticesBuffer;
	int bufferIndex;
	final Vertices vertices;
	int numSprites;
	final GLGraphics g;

	/**
	 * 
	 * @param glGraphics
	 * @param maxSprites
	 */
	public SpriteBatcher( final GLGraphics glGraphics, final int maxSprites ) {

		this.verticesBuffer = new float[ maxSprites * 4 * 4 ];
		this.vertices = new Vertices( glGraphics , maxSprites * 4 ,
				maxSprites * 6 , false , true );
		this.bufferIndex = 0;
		this.numSprites = 0;
		this.g = glGraphics;

		final short[ ] indices = new short[ maxSprites * 6 ];
		final int len = indices.length;
		short j = 0;
		for ( int i = 0 ; i < len ; i += 6, j += 4 ) {
			indices[ i + 0 ] = (short) (j + 0);
			indices[ i + 1 ] = (short) (j + 1);
			indices[ i + 2 ] = (short) (j + 2);
			indices[ i + 3 ] = (short) (j + 2);
			indices[ i + 4 ] = (short) (j + 3);
			indices[ i + 5 ] = (short) (j + 0);
		}
		this.vertices.setIndices( indices , 0 , indices.length );
	}


	/**
	 * 텍스쳐를 바인딩하고 SpriteBatcher 를 초기화하여 draw 를 준비합니다.<p>
	 * 주의 해야 하는 것은 두가지 입니다.<p>
	 * 1. beginBatch() 와 endBatch() 메서드 사이에 새로운 beginBatch() 메서드를 호출하지 마세요.<p>
	 * 2. beginBatch() 를 호출 하셧다면 반드시 endBatch() 를 호출 하셔야 합니다. 자세한 설명은 endBatch() doc 을 참고해 주세요.
	 *
	 * @param texture
	 */
	public void beginBatch(final Texture texture) {

		texture.bind( );
		this.numSprites = 0;
		this.bufferIndex = 0;
	}

	/**
	 * 반드시 beginBatch() 가 호출된 후에 호출해 주세요.<p>
	 * 그려질 픽셀들에 가해질 연산을 마친후 하나의 폴리곤에 몰아 넣고 화면에 출력합니다.
	 */
	public void endBatch( ) {

		this.vertices.setVertices( this.verticesBuffer , 0 , this.bufferIndex );
		this.vertices.bind( );
		this.vertices.draw( GL10.GL_TRIANGLES , 0 , this.numSprites * 6 );
		this.vertices.unbind( );
	}

	/**
	 * 미리 정의된 텍스쳐상의 공간을 드로우 합니다.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param region
	 */
	public void drawSprite(final float x, final float y, final float width, final float height,
			final TextureRegion region) {

		final float halfWidth = width / 2;
		final float halfHeight = height / 2;
		final float x1 = x - halfWidth;
		final float y1 = y - halfHeight;
		final float x2 = x + halfWidth;
		final float y2 = y + halfHeight;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.numSprites++;
	}

	public void drawSprite(V2 pos, final float width, final float height,
			final TextureRegion region) {

		final float halfWidth = width / 2;
		final float halfHeight = height / 2;
		final float x1 = pos.x - halfWidth;
		final float y1 = pos.y - halfHeight;
		final float x2 = pos.x + halfWidth;
		final float y2 = pos.y + halfHeight;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.numSprites++;
	}
	
	/**
	 * 미리 정의된 텍스쳐상의 공간을 드로우 합니다.<p>
	 * 특이점은 각도를 주어 회전이 가능하다는 것 입니다.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param angle
	 * @param region
	 */
	public void drawSprite(final float x, final float y, final float width, final float height,
			final float angle, final TextureRegion region) {

		final float halfWidth = width / 2;
		final float halfHeight = height / 2;

		final float rad = angle * V2.TO_RADIANS;
		final float cos = FloatMath.cos( rad );
		final float sin = FloatMath.sin( rad );

		float x1 = (-halfWidth * cos) - (-halfHeight * sin);
		float y1 = (-halfWidth * sin) + (-halfHeight * cos);
		float x2 = (halfWidth * cos) - (-halfHeight * sin);
		float y2 = (halfWidth * sin) + (-halfHeight * cos);
		float x3 = (halfWidth * cos) - (halfHeight * sin);
		float y3 = (halfWidth * sin) + (halfHeight * cos);
		float x4 = (-halfWidth * cos) - (halfHeight * sin);
		float y4 = (-halfWidth * sin) + (halfHeight * cos);

		x1 += x;
		y1 += y;
		x2 += x;
		y2 += y;
		x3 += x;
		y3 += y;
		x4 += x;
		y4 += y;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x3;
		this.verticesBuffer[ this.bufferIndex++ ] = y3;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.verticesBuffer[ this.bufferIndex++ ] = x4;
		this.verticesBuffer[ this.bufferIndex++ ] = y4;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.numSprites++;
	}
	public void drawSprite( V2 pos, final float width, final float height,
			final float angle, final TextureRegion region) {

		final float halfWidth = width / 2;
		final float halfHeight = height / 2;

		final float rad = angle * V2.TO_RADIANS;
		final float cos = FloatMath.cos( rad );
		final float sin = FloatMath.sin( rad );

		float x1 = (-halfWidth * cos) - (-halfHeight * sin);
		float y1 = (-halfWidth * sin) + (-halfHeight * cos);
		float x2 = (halfWidth * cos) - (-halfHeight * sin);
		float y2 = (halfWidth * sin) + (-halfHeight * cos);
		float x3 = (halfWidth * cos) - (halfHeight * sin);
		float y3 = (halfWidth * sin) + (halfHeight * cos);
		float x4 = (-halfWidth * cos) - (halfHeight * sin);
		float y4 = (-halfWidth * sin) + (halfHeight * cos);

		x1 += pos.x;
		y1 += pos.y;
		x2 += pos.x;
		y2 += pos.y;
		x3 += pos.x;
		y3 += pos.y;
		x4 += pos.x;
		y4 += pos.y;

		this.verticesBuffer[ this.bufferIndex++ ] = x1;
		this.verticesBuffer[ this.bufferIndex++ ] = y1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x2;
		this.verticesBuffer[ this.bufferIndex++ ] = y2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v2;

		this.verticesBuffer[ this.bufferIndex++ ] = x3;
		this.verticesBuffer[ this.bufferIndex++ ] = y3;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u2;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.verticesBuffer[ this.bufferIndex++ ] = x4;
		this.verticesBuffer[ this.bufferIndex++ ] = y4;
		this.verticesBuffer[ this.bufferIndex++ ] = region.u1;
		this.verticesBuffer[ this.bufferIndex++ ] = region.v1;

		this.numSprites++;
	}



}