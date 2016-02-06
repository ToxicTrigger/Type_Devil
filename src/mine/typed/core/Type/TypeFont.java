package mine.typed.core.Type;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import mine.typed.GL.GLGame;
import mine.typed.GL.GLGraphics;
import mine.typed.GL.SpriteBatcher;
import mine.typed.GL.Texture;
import mine.typed.GL.TextureRegion;

/**
 * 글씨를 화면에 출력하게 도와주는 클래스 이다.
 * 
 * @author mrminer
 *
 */
public class TypeFont {

    Bitmap mbit;
    Bitmap b;
    Canvas c;

    public Paint pin;
    public Typeface face;
    public GLGame glga;
    public GLGraphics gLGraphics;

    public SpriteBatcher sb;
    public Texture tex;
    public TextureRegion texr;

    public String text;

    public float fa, fr, fg, fb;

    public TypeFont(final String FontLocation, final GLGame glgame, final GLGraphics gLGraphics) {

	this.glga = glgame;
	this.face = Typeface.createFromAsset(this.glga.getAssets(), FontLocation);
	this.gLGraphics = gLGraphics;

	this.pin = new Paint();
	this.pin.setTypeface(this.face);
	this.pin.setColor(Color.BLACK);
	this.pin.setTextSize(9);
	this.pin.setAntiAlias(false);
	this.pin.setTextScaleX(1);
    }

    public void makeText(final String Text, final GLGame glga, final int Color,
	    final float TextSize, final boolean AntiA, final boolean mipmap) {

	if (this.text == Text) {
	    return;
	}

	final Bitmap mmbit = Bitmap.createBitmap(512, 128, Config.ARGB_8888);
	final Bitmap bb = Bitmap.createBitmap(512, 128, Config.ARGB_8888);

	this.pin.setTextSize(TextSize);
	this.pin.setColor(Color);
	this.pin.setAntiAlias(AntiA);

	this.text = Text;

	Canvas c = new Canvas(bb);
	c.drawBitmap(mmbit, 0, 0, null);
	c.drawText(Text, 0, this.pin.getTextSize(), this.pin);

	this.tex = new Texture(glga, bb, mipmap);
	this.texr = new TextureRegion(this.tex, 0, 0, 512, 128);

	c = null;
	bb.recycle();
	mmbit.recycle();
    }

    public void makeText(final String Text, final GLGame glga, final int a,
	    final int r, final int g, final int b, final float textSize,
	    final boolean AntiA, final boolean mipmap) {

	if (this.text == Text) {
	    return;
	}

	this.pin.setTextSize(textSize);
	this.pin.setARGB(a, r, g, b);
	this.pin.setAntiAlias(AntiA);

	this.text = Text;
	this.fa = a;
	this.fr = r;
	this.fg = g;
	this.fb = b;

	final Bitmap mmbit = Bitmap.createBitmap(512, 128, Config.ARGB_8888);
	final Bitmap bb = Bitmap.createBitmap(512, 128, Config.ARGB_8888);
	this.b = bb;

	Canvas c = new Canvas(bb);
	c.drawBitmap(mmbit, 0, 0, null);
	c.drawText(Text, 0, this.pin.getTextSize(), this.pin);
	this.tex = new Texture(glga, bb, mipmap);
	this.texr = new TextureRegion(this.tex, 0, 0, 512, 128);

	c = null;
	bb.recycle();
	mmbit.recycle();
    }

    public Texture getTexture() {

	return this.tex;
    }

    public TextureRegion getTextureRegion() {

	return this.texr;
    }

    public void drawText(final SpriteBatcher SB, final float x, final float y,
	    final float angle) {

	SB.beginBatch(this.tex);
	SB.drawSprite(x + 256, y - 64, 512, 128, angle, this.texr);
	SB.endBatch();
    }

    public void drawText(final SpriteBatcher SB, 
	    final float x, final float y,
	    final float angle, final String Text, final GLGame glga,
	    final int Color, final float textSize, final boolean AntiA,
	    final boolean mipmap) {

	this.makeText(Text, glga, Color, textSize, AntiA, mipmap);
	this.drawText(SB, x, y, angle);
    }
    
    public void reload() {
	this.tex.reloadB();
    }

}
