package mine.typed.core.Type;

import java.io.IOException;
import java.io.InputStream;

import mine.typed.core.interfaces.Graphic;
import mine.typed.core.interfaces.Pixmap;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

/**
 * 그리기 문제를 해결하게 도와주는 클래스 이다.
 * 
 * @author mrminer
 *
 */
public class TypeGraphic implements Graphic {
    AssetManager assets;
    Bitmap bitmap;
    Canvas c;
    Paint p;
    Rect srcR = new Rect();
    Rect dstR = new Rect();

    public TypeGraphic(final AssetManager assets, final Bitmap buffer) {

	this.assets = assets;
	this.bitmap = buffer;
	this.c = new Canvas(buffer);
	this.p = new Paint();
    }

    @Override
    public Pixmap newPixmap(final String filename, PixmapFormat pixmapformat) {
	Config config = null;
	if (pixmapformat == PixmapFormat.RGB565) {
	    config = Config.RGB_565;
	} else if (pixmapformat == PixmapFormat.ARGB4444) {
	    config = Config.ARGB_4444;
	} else {
	    config = Config.ARGB_8888;
	}

	final Options options = new Options();
	options.inPreferredConfig = config;

	InputStream in = null;
	Bitmap bitmap = null;

	try {
	    in = this.assets.open(filename);
	    bitmap = BitmapFactory.decodeStream(in);
	    if (bitmap == null) {
		throw new RuntimeException("Couldn't load bitmap from asset '" + filename + "'");
	    }
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't load bitmap from asset '" + filename + "'");
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (final IOException e) {
		}
	    }
	}

	if (bitmap.getConfig() == Config.RGB_565) {
	    pixmapformat = PixmapFormat.RGB565;
	} else if (bitmap.getConfig() == Config.ARGB_4444) {
	    pixmapformat = PixmapFormat.ARGB4444;
	} else {
	    pixmapformat = PixmapFormat.ARGB8888;
	}

	return new TypePixmap(bitmap, pixmapformat);
    }

    @Override
    public void clear(final int color) {
	this.c.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0xff);
    }

    @Override
    public void drawPixel(final int x, final int y, final int color) {
	this.p.setColor(color);
	this.c.drawPoint(x, y, this.p);

    }

    @Override
    public void drawLine(final int x, final int y, final int x2, final int y2,
	    final int color) {
	this.p.setColor(color);
	this.c.drawLine(x, y, x2, y2, this.p);
    }

    @Override
    public void drawRect(final int x, final int y, final int w, final int h,
	    final int color) {
	this.p.setColor(color);
	this.p.setStyle(Style.FILL);
	this.c.drawRect(x, y, (x + w) - 1, (y + h) - 1, this.p);
    }

    public void drawPixmap(final Pixmap pixmap, final int x, final int y,
	    final int srcX, final int srcY, final int srcW, final int srcH,
	    final int alpha) {
	this.srcR.left = srcX;
	this.srcR.top = srcY;
	this.srcR.right = (srcX + srcW) - 1;
	this.srcR.bottom = (srcY + srcH) - 1;

	this.dstR.left = srcX;
	this.dstR.top = srcY;
	this.dstR.right = (srcX + srcW) - 1;
	this.dstR.bottom = (srcY + srcH) - 1;
	this.p.setAlpha(alpha);

	this.c.drawBitmap(((TypePixmap) pixmap).mbit, this.srcR, this.dstR, this.p);
    }

    public void drawPixmap(final Pixmap pixmap, final int x, final int y,
	    final int alpha) {
	this.p.setAlpha(alpha);
	this.c.drawBitmap(((TypePixmap) pixmap).mbit, x, y, this.p);
    }

    @Override
    public int getW() {
	return this.bitmap.getWidth();
    }

    @Override
    public int getH() {
	return this.bitmap.getHeight();
    }

    @Override
    public void drawPixmap(final Pixmap pixmap, final int x, final int y,
	    final int srcX, final int srcY, final int srcW, final int srcH) {
	this.srcR.left = srcX;
	this.srcR.top = srcY;
	this.srcR.right = (srcX + srcW) - 1;
	this.srcR.bottom = (srcY + srcH) - 1;

	this.dstR.left = srcX;
	this.dstR.top = srcY;
	this.dstR.right = (srcX + srcW) - 1;
	this.dstR.bottom = (srcY + srcH) - 1;

	this.c.drawBitmap(((TypePixmap) pixmap).mbit, this.srcR, this.dstR, null);
    }

    @Override
    public void drawPixmap(final Pixmap pixmap, final int x, final int y) {
	this.c.drawBitmap(((TypePixmap) pixmap).mbit, x, y, null);

    }

}
