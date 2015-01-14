
package mine.typed.GL;



import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;



public class Vertices {
	final GLGraphics glGraphics;
	final boolean hasColor;
	final boolean hasTexCoords;
	final int vertexSize;
	final IntBuffer vertices;
	final int[ ] tmpBuffer;
	final ShortBuffer indices;

	public Vertices( final GLGraphics glGraphics, final int maxVertices, final int maxIndices,
			final boolean hasColor, final boolean hasTexCoords ) {

		this.glGraphics = glGraphics;
		this.hasColor = hasColor;
		this.hasTexCoords = hasTexCoords;
		this.vertexSize = (2 + (hasColor ? 4 : 0) + (hasTexCoords ? 2 : 0)) * 4;
		this.tmpBuffer = new int[ (maxVertices * this.vertexSize) / 4 ];

		ByteBuffer buffer = ByteBuffer
				.allocateDirect( maxVertices * this.vertexSize );
		buffer.order( ByteOrder.nativeOrder( ) );
		this.vertices = buffer.asIntBuffer( );

		if ( maxIndices > 0 ) {
			buffer = ByteBuffer.allocateDirect( (maxIndices * Short.SIZE) / 8 );
			buffer.order( ByteOrder.nativeOrder( ) );
			this.indices = buffer.asShortBuffer( );
		} else {
			this.indices = null;
		}
	}

	public void setVertices(final float[ ] vertices, final int offset, final int length) {

		this.vertices.clear( );
		final int len = offset + length;
		for ( int i = offset, j = 0 ; i < len ; i++, j++ ) {
			this.tmpBuffer[ j ] = Float.floatToRawIntBits( vertices[ i ] );
		}
		this.vertices.put( this.tmpBuffer , 0 , length );
		this.vertices.flip( );
	}

	public void setIndices(final short[ ] indices, final int offset, final int length) {

		this.indices.clear( );
		this.indices.put( indices , offset , length );
		this.indices.flip( );
	}

	public void bind( ) {

		final GL10 gl = this.glGraphics.getGL( );

		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		this.vertices.position( 0 );
		gl.glVertexPointer( 2 , GL10.GL_FLOAT , this.vertexSize , this.vertices );

		if ( this.hasColor ) {
			gl.glEnableClientState( GL10.GL_COLOR_ARRAY );
			this.vertices.position( 2 );
			gl.glColorPointer( 4 , GL10.GL_FLOAT , this.vertexSize , this.vertices );
		}

		if ( this.hasTexCoords ) {
			gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
			this.vertices.position( this.hasColor ? 6 : 2 );
			gl.glTexCoordPointer( 2 , GL10.GL_FLOAT , this.vertexSize , this.vertices );
		}
	}

	public void draw(final int primitiveType, final int offset, final int numVertices) {

		final GL10 gl = this.glGraphics.getGL( );

		if ( this.indices != null ) {
			this.indices.position( offset );
			gl.glDrawElements( primitiveType , numVertices ,
					GL10.GL_UNSIGNED_SHORT , this.indices );
		} else {
			gl.glDrawArrays( primitiveType , offset , numVertices );
		}
	}

	public void unbind( ) {

		final GL10 gl = this.glGraphics.getGL( );
		if ( this.hasTexCoords ) {
			gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		}

		if ( this.hasColor ) {
			gl.glDisableClientState( GL10.GL_COLOR_ARRAY );
		}
	}
}
