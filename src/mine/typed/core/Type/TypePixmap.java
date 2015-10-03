package mine.typed.core.Type;

import mine.typed.core.interfaces.Graphic.PixmapFormat;
import mine.typed.core.interfaces.Pixmap;
import android.graphics.Bitmap;

public class TypePixmap implements Pixmap {
    Bitmap mbit;
    PixmapFormat format;
    TypePixmap singturn;
    boolean turn;

    public TypePixmap(final Bitmap bitmap, final PixmapFormat format) {

	this.mbit = bitmap;
	this.format = format;
    }

    public TypePixmap getInstance(final Bitmap bitmap, final PixmapFormat format) {

	if (this.singturn == null) {
	    this.singturn = new TypePixmap(bitmap, format);
	}
	return this.singturn;
    }

    @Override
    public int getW() {

	return this.mbit.getWidth();
    }

    @Override
    public int getH() {

	return this.mbit.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {

	return this.format;
    }

    @Override
    public void dispose() {

	this.mbit.recycle();
    }

}
