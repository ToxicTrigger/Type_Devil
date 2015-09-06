package mine.typed.core.Type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mine.typed.core.interfaces.FileIO;
import android.content.res.AssetManager;
import android.os.Environment;

/**
 * 외부로의 파일 입출력을 원활하게 해주는 helper 클래스
 * 
 * @author mrminer
 *
 */
public class TypeFileIO implements FileIO
{

	AssetManager assets;
	String espath;

	public TypeFileIO(final AssetManager assets)
	{

		this.assets = assets;
		this.espath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

	}

	@Override
	public InputStream readAsset(final String fileName) throws IOException
	{

		return this.assets.open(fileName);
	}

	@Override
	public InputStream readFile(final String fileName) throws IOException
	{

		System.out.println(espath + fileName);

		return new FileInputStream(this.espath + fileName);
	}

	@Override
	public FileOutputStream writeFile(final String fileName) throws IOException
	{
		return new FileOutputStream(this.espath + fileName);
	}

}
