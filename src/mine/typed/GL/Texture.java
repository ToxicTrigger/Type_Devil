package mine.typed.GL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import mine.tools.debug.TypeLogger;
import mine.typed.core.interfaces.FileIO;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * 비트맵 이미지를 Texture 로 변환하여 사용하도록 해줍니다.
 * 
 * @author mrminer
 *
 */
public class Texture {
    
    public static final String TEMP_TEX_NAME = "tmpTex.png";

    GLGraphics glGraphics;
    FileIO fileIO;
    String fileName;
    private int textureId;
    int minFilter;
    int magFilter;
    public int width;
    public int height;
    boolean mipmapped;
    public Bitmap bit;

    private final boolean isInAssets;

    public boolean hasInAssets() {
	return isInAssets;
    }

    /**
     * 밉맵 적용없는 생성자.
     * 
     * @param glGame
     * @param fileName
     */
    public Texture(GLGame glGame, String fileName, boolean InAssets) {
	this(glGame, fileName, false, InAssets);
    }

    /**
     * 밉맵을 생성합니다. 원본과 최대한 비슷한 퀄리티로 생성합니다.
     * 
     * @param glGame
     * @param fileName
     * @param mipmap
     */
    public Texture(final GLGame glGame, final String fileName, final boolean mipmapped, boolean InAssets) {

	this.glGraphics = glGame.getGLGraphics();
	this.fileIO = glGame.getFileIO();
	this.fileName = fileName;
	this.mipmapped = mipmapped;
	
	this.isInAssets = InAssets;
	if (!isInAssets) {
	    this.loadOnEnvPath();
	} else {
	    try {
		this.load();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public Texture(final GLGame glGame, final String fileName) {
	this(glGame, fileName, false, true);
    }

    /**
     * 비트맵 리소스를 따로 얻어와 텍스쳐를 만듭니다. 런레벨에서 텍스쳐를 생성하려는 목적으로 생성되었습니다.
     * 
     * @param glGame
     * @param bitmap
     * @param mipmapped
     */
    public Texture(final GLGame glGame, final Bitmap bitmap,
	    final boolean mipmapped) {

	this.glGraphics = glGame.getGLGraphics();
	// this.fileIO = glGame.getFileIO();
	this.bit = bitmap;
	this.mipmapped = mipmapped;
	this.isInAssets = false;
	this.loadonBitmap();

    }

    public static Bitmap fastblur(Bitmap sentBitmap, float scale, int radius) {

	int width = Math.round(sentBitmap.getWidth() * scale);
	int height = Math.round(sentBitmap.getHeight() * scale);
	sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

	Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
	//Bitmap bitmap = Bitmap.createBitmap(sentBitmap);
	
	if (radius < 1) {
	    return (null);
	}

	int w = bitmap.getWidth();
	int h = bitmap.getHeight();

	int[] pix = new int[w * h];

	bitmap.getPixels(pix, 0, w, 0, 0, w, h);

	int wm = w - 1;
	int hm = h - 1;
	int wh = w * h;
	int div = radius + radius + 1;

	int r[] = new int[wh];
	int g[] = new int[wh];
	int b[] = new int[wh];
	int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
	int vmin[] = new int[Math.max(w, h)];

	int divsum = (div + 1) >> 1;
	divsum *= divsum;
	int dv[] = new int[256 * divsum];
	for (i = 0; i < 256 * divsum; i++) {
	    dv[i] = (i / divsum);
	}

	yw = yi = 0;

	int[][] stack = new int[div][3];
	int stackpointer;
	int stackstart;
	int[] sir;
	int rbs;
	int r1 = radius + 1;
	int routsum, goutsum, boutsum;
	int rinsum, ginsum, binsum;

	for (y = 0; y < h; y++) {
	    rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
	    for (i = -radius; i <= radius; i++) {
		p = pix[yi + Math.min(wm, Math.max(i, 0))];
		sir = stack[i + radius];
		sir[0] = (p & 0xff0000) >> 16;
		sir[1] = (p & 0x00ff00) >> 8;
		sir[2] = (p & 0x0000ff);
		rbs = r1 - Math.abs(i);
		rsum += sir[0] * rbs;
		gsum += sir[1] * rbs;
		bsum += sir[2] * rbs;
		if (i > 0) {
		    rinsum += sir[0];
		    ginsum += sir[1];
		    binsum += sir[2];
		} else {
		    routsum += sir[0];
		    goutsum += sir[1];
		    boutsum += sir[2];
		}
	    }
	    stackpointer = radius;

	    for (x = 0; x < w; x++) {

		r[yi] = dv[rsum];
		g[yi] = dv[gsum];
		b[yi] = dv[bsum];

		rsum -= routsum;
		gsum -= goutsum;
		bsum -= boutsum;

		stackstart = stackpointer - radius + div;
		sir = stack[stackstart % div];

		routsum -= sir[0];
		goutsum -= sir[1];
		boutsum -= sir[2];

		if (y == 0) {
		    vmin[x] = Math.min(x + radius + 1, wm);
		}
		p = pix[yw + vmin[x]];

		sir[0] = (p & 0xff0000) >> 16;
		sir[1] = (p & 0x00ff00) >> 8;
		sir[2] = (p & 0x0000ff);

		rinsum += sir[0];
		ginsum += sir[1];
		binsum += sir[2];

		rsum += rinsum;
		gsum += ginsum;
		bsum += binsum;

		stackpointer = (stackpointer + 1) % div;
		sir = stack[(stackpointer) % div];

		routsum += sir[0];
		goutsum += sir[1];
		boutsum += sir[2];

		rinsum -= sir[0];
		ginsum -= sir[1];
		binsum -= sir[2];

		yi++;
	    }
	    yw += w;
	}
	for (x = 0; x < w; x++) {
	    rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
	    yp = -radius * w;
	    for (i = -radius; i <= radius; i++) {
		yi = Math.max(0, yp) + x;

		sir = stack[i + radius];

		sir[0] = r[yi];
		sir[1] = g[yi];
		sir[2] = b[yi];

		rbs = r1 - Math.abs(i);

		rsum += r[yi] * rbs;
		gsum += g[yi] * rbs;
		bsum += b[yi] * rbs;

		if (i > 0) {
		    rinsum += sir[0];
		    ginsum += sir[1];
		    binsum += sir[2];
		} else {
		    routsum += sir[0];
		    goutsum += sir[1];
		    boutsum += sir[2];
		}

		if (i < hm) {
		    yp += w;
		}
	    }
	    yi = x;
	    stackpointer = radius;
	    for (y = 0; y < h; y++) {
		// Preserve alpha channel: ( 0xff000000 & pix[yi] )
		pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

		rsum -= routsum;
		gsum -= goutsum;
		bsum -= boutsum;

		stackstart = stackpointer - radius + div;
		sir = stack[stackstart % div];

		routsum -= sir[0];
		goutsum -= sir[1];
		boutsum -= sir[2];

		if (x == 0) {
		    vmin[y] = Math.min(y + r1, hm) * w;
		}
		p = x + vmin[y];

		sir[0] = r[p];
		sir[1] = g[p];
		sir[2] = b[p];

		rinsum += sir[0];
		ginsum += sir[1];
		binsum += sir[2];

		rsum += rinsum;
		gsum += ginsum;
		bsum += binsum;

		stackpointer = (stackpointer + 1) % div;
		sir = stack[stackpointer];

		routsum += sir[0];
		goutsum += sir[1];
		boutsum += sir[2];

		rinsum -= sir[0];
		ginsum -= sir[1];
		binsum -= sir[2];

		yi += w;
	    }
	}

	bitmap.setPixels(pix, 0, w, 0, 0, w, h);

	return (bitmap);
    }

    /**
     * 텍스쳐로 적용될 비트맵을 직접 지정합니다.
     * 
     * @param bitmap
     */
    public void setBitmap(final Bitmap bitmap) {
	this.bit = bitmap;
    }

    /**
     * 
     * @return 텍스쳐로 적용된 비트맵을 반환합니다.
     */
    public Bitmap getBitmap(){
	InputStream in = null;
	Bitmap bitmap = null;
	try {
	    in = this.fileIO.readAsset(this.fileName);
	    bitmap = BitmapFactory.decodeStream(in);
	    in.close();
		return bitmap;
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't load texture '" + this.fileName + "'", e);
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (final IOException e) {
		}
	    }
	}
    }

    private void loadonBitmap() {

	final GL10 gl = this.glGraphics.getGL();
	final int[] textureIds = new int[1];
	gl.glGenTextures(1, textureIds, 0);
	this.textureId = textureIds[0];

	try {
	    Bitmap bitmap = this.bit;
	    if (this.mipmapped) {
		this.createMipmaps(gl, bitmap);
	    } else {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		this.setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();

		 bitmap = null;
	    }

	} catch (final Exception e) {
	    Log.e("<Texture>", "Error Message is " + e.getMessage());
	}

    }
    
    boolean isEndScope;
    private void load() throws Exception {

	final GL10 gl = this.glGraphics.getGL();
	final int[] textureIds = new int[1];
	gl.glGenTextures(1, textureIds, 0);
	this.textureId = textureIds[0];

	InputStream in = null;
	try {
	    in = this.fileIO.readAsset(this.fileName);
	    Bitmap bitmap = BitmapFactory.decodeStream(in);
	    if (this.mipmapped) {
		this.createMipmaps(gl, bitmap);
	    } else {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		this.setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
		bitmap = null;
	    }
	} catch (FileNotFoundException e) {
	    //throw new RuntimeException("Couldn't load texture '" + this.fileName + "'", e);

	    if(!isEndScope){
		    TypeLogger.getInstance().printMsg(this, "<RuntimeException>", "Couldn't load texture '" + this.fileName + "'" + e.getMessage());
		    TypeLogger.getInstance().printMsg(this, "<Texture>", "so loaded " + TEMP_TEX_NAME );
		    this.fileName = TEMP_TEX_NAME;
		    isEndScope = true;
		    load();
	    }

	}finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (final IOException e) {
		}
	    }
	}
    }

    private void loadOnEnvPath() {

	final GL10 gl = this.glGraphics.getGL();
	final int[] textureIds = new int[1];
	gl.glGenTextures(1, textureIds, 0);
	this.textureId = textureIds[0];

	InputStream in = null;
	try {
	    in = this.fileIO.readFile(this.fileName);
	    Bitmap bitmap = BitmapFactory.decodeStream(in);
	    if (this.mipmapped) {
		this.createMipmaps(gl, bitmap);
	    } else {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		this.setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();

		bitmap = null;
	    }
	} catch (final IOException e) {
	    throw new RuntimeException("Couldn't load texture '" + this.fileName + "'", e);
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (final IOException e) {
		}
	    }
	}
    }

    private void createMipmaps(final GL10 gl, Bitmap bitmap) {

	gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
	this.width = bitmap.getWidth();
	this.height = bitmap.getHeight();
	this.setFilters(GL10.GL_LINEAR_MIPMAP_NEAREST, GL10.GL_LINEAR);

	int level = 0;
	int newWidth = this.width;
	int newHeight = this.height;
	while (true) {
	    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, level, bitmap, 0);
	    newWidth = newWidth / 2;
	    newHeight = newHeight / 2;
	    if (newWidth <= 0) {
		break;
	    }
	    final Bitmap newBitmap = Bitmap.createBitmap(newWidth, newHeight, bitmap.getConfig());
	    final Canvas canvas = new Canvas(newBitmap);
	    canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, newWidth, newHeight), null);
	    bitmap.recycle();
	    bitmap = newBitmap;
	    level++;
	}

	gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
	bitmap = null;
    }

    /**
     * 생성된 텍스쳐를 다시 초기화 합니다.
     */
    public void reload() {

	try {
	    this.load();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.bind();
	this.setFilters(this.minFilter, this.magFilter);
	this.glGraphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }

    /**
     * 직접 비트맵을 설정받은 텍스쳐를 다시 초기화 합니다.
     */
    public void reloadB() {

	this.loadonBitmap();
	this.bind();
	this.setFilters(this.minFilter, this.magFilter);
	this.glGraphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }

    /**
     * 밉맵 생성시 사용되는 메서드 입니다. 밉맵의 품질이 결정됩니다.
     * 
     * @param minFilter
     * @param magFilter
     */
    public void setFilters(final int minFilter, final int magFilter) {

	this.minFilter = minFilter;
	this.magFilter = magFilter;
	final GL10 gl = this.glGraphics.getGL();
	gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
	gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    /**
     * 바인드 합니다. 이는 현재 생성된 텍스쳐를 메모리에 업로드 한다는 의미 입니다.
     */
    public void bind() {

	final GL10 gl = this.glGraphics.getGL();
	gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
    }

    /**
     * 사용이 끝난 텍스쳐를 메모리에서 지울때 사용됩니다. 내부적으로 이 메서드는 사용된 텍스쳐의 ID를 지웁니다.
     */
    public void dispose() {

	final GL10 gl = this.glGraphics.getGL();
	gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textureId);
	final int[] textureIds = { this.textureId };
	gl.glDeleteTextures(1, textureIds, 0);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bit == null) ? 0 : bit.hashCode());
	result = prime * result + ((fileIO == null) ? 0 : fileIO.hashCode());
	result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
	result = prime * result + ((glGraphics == null) ? 0 : glGraphics
		.hashCode());
	result = prime * result + height;
	result = prime * result + magFilter;
	result = prime * result + minFilter;
	result = prime * result + (mipmapped ? 1231 : 1237);
	result = prime * result + textureId;
	result = prime * result + width;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Texture other = (Texture) obj;
	if (bit == null) {
	    if (other.bit != null)
		return false;
	} else if (!bit.equals(other.bit))
	    return false;
	if (fileIO == null) {
	    if (other.fileIO != null)
		return false;
	} else if (!fileIO.equals(other.fileIO))
	    return false;
	if (fileName == null) {
	    if (other.fileName != null)
		return false;
	} else if (!fileName.equals(other.fileName))
	    return false;
	if (glGraphics == null) {
	    if (other.glGraphics != null)
		return false;
	} else if (!glGraphics.equals(other.glGraphics))
	    return false;
	if (height != other.height)
	    return false;
	if (magFilter != other.magFilter)
	    return false;
	if (minFilter != other.minFilter)
	    return false;
	if (mipmapped != other.mipmapped)
	    return false;
	if (textureId != other.textureId)
	    return false;
	if (width != other.width)
	    return false;
	return true;
    }

}