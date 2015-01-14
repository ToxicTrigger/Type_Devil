
package mine.typed.core;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mine.typed.GL.GLGame;
import mine.typed.GL.Vertices3;



/**
 * .obj 파일을 로드 하기 위한 로더 입니다.
 * 3D 모델을 불러 옵니다.
 * @author mrminer
 *
 */
public class OBJLoader {
	public static Vertices3 load(final GLGame game, final String file) {

		InputStream in = null;
		try {
			in = game.getFileIO( ).readAsset( file );
			final List<String> lines = OBJLoader.readLines( in );

			final float[ ] vertices = new float[ lines.size( ) * 3 ];
			final float[ ] normals = new float[ lines.size( ) * 3 ];
			final float[ ] uv = new float[ lines.size( ) * 2 ];

			int numVertices = 0;
			int numNormals = 0;
			int numUV = 0;
			int numFaces = 0;

			final int[ ] facesVerts = new int[ lines.size( ) * 3 ];
			final int[ ] facesNormals = new int[ lines.size( ) * 3 ];
			final int[ ] facesUV = new int[ lines.size( ) * 3 ];
			int vertexIndex = 0;
			int normalIndex = 0;
			int uvIndex = 0;
			int faceIndex = 0;

			for ( int i = 0 ; i < lines.size( ) ; i++ ) {
				final String line = lines.get( i );
				if ( line.startsWith( "v " ) ) {
					final String[ ] tokens = line.split( "[ ]+" );
					vertices[ vertexIndex ] = Float.parseFloat( tokens[ 1 ] );
					vertices[ vertexIndex + 1 ] = Float
							.parseFloat( tokens[ 2 ] );
					vertices[ vertexIndex + 2 ] = Float
							.parseFloat( tokens[ 3 ] );
					vertexIndex += 3;
					numVertices++;
					continue;
				}

				if ( line.startsWith( "vn " ) ) {
					final String[ ] tokens = line.split( "[ ]+" );
					normals[ normalIndex ] = Float.parseFloat( tokens[ 1 ] );
					normals[ normalIndex + 1 ] = Float.parseFloat( tokens[ 2 ] );
					normals[ normalIndex + 2 ] = Float.parseFloat( tokens[ 3 ] );
					normalIndex += 3;
					numNormals++;
					continue;
				}

				if ( line.startsWith( "vt" ) ) {
					final String[ ] tokens = line.split( "[ ]+" );
					uv[ uvIndex ] = Float.parseFloat( tokens[ 1 ] );
					uv[ uvIndex + 1 ] = Float.parseFloat( tokens[ 2 ] );
					uvIndex += 2;
					numUV++;
					continue;
				}

				if ( line.startsWith( "f " ) ) {
					final String[ ] tokens = line.split( "[ ]+" );

					String[ ] parts = tokens[ 1 ].split( "/" );
					facesVerts[ faceIndex ] = OBJLoader.getIndex( parts[ 0 ] ,
							numVertices );
					if ( parts.length > 2 ) {
						facesNormals[ faceIndex ] = OBJLoader.getIndex(
								parts[ 2 ] , numNormals );
					}
					if ( parts.length > 1 ) {
						facesUV[ faceIndex ] = OBJLoader.getIndex( parts[ 1 ] ,
								numUV );
					}
					faceIndex++;

					parts = tokens[ 2 ].split( "/" );
					facesVerts[ faceIndex ] = OBJLoader.getIndex( parts[ 0 ] ,
							numVertices );
					if ( parts.length > 2 ) {
						facesNormals[ faceIndex ] = OBJLoader.getIndex(
								parts[ 2 ] , numNormals );
					}
					if ( parts.length > 1 ) {
						facesUV[ faceIndex ] = OBJLoader.getIndex( parts[ 1 ] ,
								numUV );
					}
					faceIndex++;

					parts = tokens[ 3 ].split( "/" );
					facesVerts[ faceIndex ] = OBJLoader.getIndex( parts[ 0 ] ,
							numVertices );
					if ( parts.length > 2 ) {
						facesNormals[ faceIndex ] = OBJLoader.getIndex(
								parts[ 2 ] , numNormals );
					}
					if ( parts.length > 1 ) {
						facesUV[ faceIndex ] = OBJLoader.getIndex( parts[ 1 ] ,
								numUV );
					}
					faceIndex++;
					numFaces++;
					continue;
				}
			}

			final float[ ] verts = new float[ numFaces * 3
			                                  * (3 + (numNormals > 0 ? 3 : 0) + (numUV > 0 ? 2 : 0)) ];

			for ( int i = 0, vi = 0 ; i < (numFaces * 3) ; i++ ) {
				final int vertexIdx = facesVerts[ i ] * 3;
				verts[ vi++ ] = vertices[ vertexIdx ];
				verts[ vi++ ] = vertices[ vertexIdx + 1 ];
				verts[ vi++ ] = vertices[ vertexIdx + 2 ];

				if ( numUV > 0 ) {
					final int uvIdx = facesUV[ i ] * 2;
					verts[ vi++ ] = uv[ uvIdx ];
					verts[ vi++ ] = 1 - uv[ uvIdx + 1 ];
				}

				if ( numNormals > 0 ) {
					final int normalIdx = facesNormals[ i ] * 3;
					verts[ vi++ ] = normals[ normalIdx ];
					verts[ vi++ ] = normals[ normalIdx + 1 ];
					verts[ vi++ ] = normals[ normalIdx + 2 ];
				}
			}

			final Vertices3 model = new Vertices3( game.getGLGraphics( ) ,
					numFaces * 3 , 0 , false , numUV > 0 , numNormals > 0 );
			model.setVertices( verts , 0 , verts.length );
			return model;
		} catch ( final Exception ex ) {
			throw new RuntimeException( "couldn't load '" + file + "'" , ex );
		} finally {
			if ( in != null ) {
				try {
					in.close( );
				} catch ( final Exception ex ) {

				}
			}
		}
	}

	static int getIndex(final String index, final int size) {

		final int idx = Integer.parseInt( index );
		if ( idx < 0 ) {
			return size + idx;
		} else {
			return idx - 1;
		}
	}

	static List<String> readLines(final InputStream in) throws IOException {

		final List<String> lines = new ArrayList<String>( );

		final BufferedReader reader = new BufferedReader(
				new InputStreamReader( in ) );
		String line = null;
		while ( (line = reader.readLine( )) != null ) {
			lines.add( line );
		}
		return lines;
	}
}
