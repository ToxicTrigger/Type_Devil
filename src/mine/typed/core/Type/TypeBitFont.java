package mine.typed.core.Type;

import mine.typed.GL.SpriteBatcher;
import mine.typed.GL.Texture;
import mine.typed.GL.TextureRegion;

/**
 * 이미지를 사용하여 글씨를 출력하도록 해주는 기능을 구현한 클래스
 * 
 * @author mrminer
 *
 */
public class TypeBitFont
{

	public final Texture texture;
	public final int glyphWidth;
	public final int glyphHeight;
	public final TextureRegion[] glyphs = new TextureRegion[ 96 ];

	/**
	 * 
	 * @param 이미지
	 * @param 글씨가
	 *            시작되는 곳의 좌상단 꼭짓점
	 * @param 글씨가
	 *            시작되는 곳의 좌상단 꼭짓점
	 * @param 글씨의
	 *            갯수
	 * @param 이미지에서
	 *            글씨의 가로 길이
	 * @param 이미지에서
	 *            글씨의 세로 길이
	 */
	public TypeBitFont(Texture texture, int offsetX, int offsetY, int glyphsPerRow, int glyphWidth, int glyphHeight)
	{
		this.texture = texture;
		this.glyphWidth = glyphWidth;
		this.glyphHeight = glyphHeight;
		int x = offsetX;
		int y = offsetY;
		for ( int i = 0; i < 96; i++ )
		{
			this.glyphs[ i ] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
			x += glyphWidth;
			if( x == (offsetX + (glyphsPerRow * glyphWidth)) )
			{
				x = offsetX;
				y += glyphHeight;
			}
		}
	}

	/**
	 * text 를 그립니다. 인자로 받는 batcher 는 그저 draw 를 호출합니다.
	 * 
	 * @param batcher
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawText(SpriteBatcher batcher, String text, float x, float y, float angle)
	{
		final int len = text.length();
		for ( int i = 0; i < len; i++ )
		{
			final int c = text.charAt(i) - ' ';
			if( (c < 0) || (c > (this.glyphs.length - 1)) ) continue;

			final TextureRegion glyph = this.glyphs[ c ];
			batcher.drawSprite(x, y, this.glyphWidth, this.glyphHeight, angle, glyph);
			x += this.glyphWidth;
		}
	}

	/**
	 * text 를 그립니다. 인자로 받는 batcher 는 그저 draw 를 호출합니다.
	 * 
	 * @param batcher
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawText(SpriteBatcher batcher, String text, float x, float y)
	{
		final int len = text.length();
		for ( int i = 0; i < len; i++ )
		{
			final int c = text.charAt(i) - ' ';
			if( (c < 0) || (c > (this.glyphs.length - 1)) ) continue;

			final TextureRegion glyph = this.glyphs[ c ];
			batcher.drawSprite(x, y, this.glyphWidth, this.glyphHeight, glyph);
			x += this.glyphWidth;
		}
	}

	/**
	 * text를 그립니다. 인자로 받은 Bathcer 는 그저 draw 호출을 위하여 받아 들입니다.
	 * 
	 * @param batcher
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawText(SpriteBatcher batcher, String text, float x, float y, float w, float h)
	{
		final int len = text.length();
		for ( int i = 0; i < len; i++ )
		{
			final int c = text.charAt(i) - ' ';
			if( (c < 0) || (c > (this.glyphs.length - 1)) ) continue;

			final TextureRegion glyph = this.glyphs[ c ];
			batcher.drawSprite(x, y, this.glyphWidth + w, this.glyphHeight + h, glyph);
			x += this.glyphWidth + w;
		}
	}

	/**
	 * text를 그립니다. 인자로 받은 Bathcer 는 그저 draw 호출을 위하여 받아 들입니다.
	 * 
	 * @param batcher
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param angle
	 */
	public void drawText(SpriteBatcher batcher, String text, float x, float y, float w, float h, float angle)
	{
		final int len = text.length();
		for ( int i = 0; i < len; i++ )
		{
			final int c = text.charAt(i) - ' ';
			if( (c < 0) || (c > (this.glyphs.length - 1)) ) continue;

			final TextureRegion glyph = this.glyphs[ c ];
			batcher.drawSprite(x, y, this.glyphWidth + w, this.glyphHeight + h, angle, glyph);
			x += this.glyphWidth + w;
		}
	}

}
