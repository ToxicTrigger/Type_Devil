package mine.typed.GL;

import javax.microedition.khronos.opengles.GL10;

import mine.typed.core.Circle;
import mine.typed.core.Rectangle;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 이 클래스는 여러가지 드로우 기능을 제공한다. 사각형 그리기나 선 , 그리고 이미지에 색을 입히는 작업이나 투명도를 제어하기 쉽게 만들어줄
 * 목적으로 만들어졌다.
 */
public class TypeDraw
{

	public SpriteBatcher sb;
	public GLGame glg;

	public Texture tex;
	public TextureRegion texr;
	Bitmap mbit;
	Bitmap b;
	Canvas c;
	public Paint paint;

	public TypeDraw(final SpriteBatcher SB, final GLGame glga)
	{

		this.sb = SB;
		this.glg = glga;
		this.paint = new Paint();
	}

	public void makeRect(final Rectangle rect)
	{

		final Bitmap mmbit = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		final Bitmap bb = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		Canvas c = new Canvas(bb);
		c.drawBitmap(mmbit, 0, 0, null);
		c.drawRect(rect.lowerLeft.x, rect.lowerLeft.y, rect.width, rect.height, this.paint);
		this.tex = new Texture(this.glg, bb, false);
		this.texr = new TextureRegion(this.tex, 0, 0, 512, 512);
		c = null;
		mmbit.recycle();
		bb.recycle();
	}

	public void makeRect(final Rect rect)
	{

		final Bitmap mmbit = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		final Bitmap bb = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		Canvas c = new Canvas(bb);
		c.drawBitmap(mmbit, 0, 0, null);
		c.drawRect(rect.left, rect.top, rect.right, rect.bottom, this.paint);
		this.tex = new Texture(this.glg, bb, false);
		this.texr = new TextureRegion(this.tex, 0, 0, 512, 512);
		c = null;
		mmbit.recycle();
		bb.recycle();
	}

	public void makeRect(final float L, final float T, final float R, final float B)
	{

		final Bitmap mmbit = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		final Bitmap bb = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		Canvas c = new Canvas(bb);
		c.drawBitmap(mmbit, 0, 0, null);
		c.drawRect(L, T, R, B, this.paint);
		this.tex = new Texture(this.glg, bb, false);
		this.texr = new TextureRegion(this.tex, 0, 0, 512, 512);
		c = null;
		mmbit.recycle();
		bb.recycle();
	}

	public void makeCircle(final Circle cir)
	{

		final Bitmap mmbit = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		final Bitmap bb = Bitmap.createBitmap(512, 512, Config.ARGB_8888);
		Canvas c = new Canvas(bb);
		c.drawBitmap(mmbit, 0, 0, null);
		c.drawCircle(cir.center.x, cir.center.y, cir.radius, this.paint);
		this.tex = new Texture(this.glg, bb, false);
		this.texr = new TextureRegion(this.tex, 0, 0, 512, 512);
		c = null;
		mmbit.recycle();
		bb.recycle();
	}

	public void drawTexture(final SpriteBatcher sb, final float x, final float y, final float angle)
	{

		sb.beginBatch(this.tex);
		sb.drawSprite(x - 256, y + 256, 512, 512, angle, this.texr);
		sb.endBatch();
	}

	/**
	 * set 메서드로 정해진 텍스쳐를 그리는데 색지정이나 투명도 등을 그리는 코드를 간결하게 만들어준다.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param angle
	 * @param A
	 * @param R
	 * @param G
	 * @param B
	 * @param gl
	 */
	public void drawTexture(final SpriteBatcher sb, final float x, final float y, final float w, final float h, final float angle, final float A, final float R, final float G, final float B, final GL10 gl)
	{

		gl.glColor4f(R, G, B, A);
		sb.beginBatch(this.tex);
		sb.drawSprite(x, y, w, h, angle, this.texr);
		sb.endBatch();
		gl.glColor4f(1, 1, 1, 1);

	}

	public void setTextuer(final Texture Tex)
	{

		this.tex = Tex;
	}

	public void setTextureRegion(final TextureRegion Texr)
	{

		this.texr = Texr;
	}

	public Texture getTexture()
	{

		return this.tex;
	}

	public TextureRegion getTextureRegion()
	{

		return this.texr;
	}

	public void drawTextureRegion(final Texture T, final float x, final float y, final float w, final float h, final float a, final float r, final float g, final float b, final float angle, final GL10 gl, final TextureRegion Tr, final boolean draw)
	{
		if( draw )
		{
			gl.glColor4f(r, g, b, a);
			this.sb.beginBatch(T);
			this.sb.drawSprite(x, y, w, h, angle, Tr);
			this.sb.endBatch();
			gl.glColor4f(1, 1, 1, 1);
		}
	}

}
