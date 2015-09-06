package mine.typed.core.interfaces;

import mine.typed.core.interfaces.Graphic.PixmapFormat;

public interface Pixmap
{

	public int getW();

	public int getH();

	public PixmapFormat getFormat();

	public void dispose();

}
