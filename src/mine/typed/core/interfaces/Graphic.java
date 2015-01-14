
package mine.typed.core.interfaces;



public interface Graphic {

	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Pixmap newPixmap(String filename, PixmapFormat format);

	public void clear(int color);

	public void drawPixel(int x, int y, int color);
	public void drawLine(int x, int y, int x2, int y2, int color);
	public void drawRect(int x, int y, int w, int h, int color);
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcW, int srcH);
	public void drawPixmap(Pixmap pixmap, int x, int y);

	public int getW( );
	public int getH( );

}
