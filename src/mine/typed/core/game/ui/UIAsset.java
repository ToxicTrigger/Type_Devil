package mine.typed.core.game.ui;

import java.util.Map;

import mine.typed.GL.GLGame;
import mine.typed.GL.Texture;
import mine.typed.GL.TextureRegion;

public class UIAsset extends Texture
{

	public final static int MAX_TEXTURE_REGION_NUMBERS = 9;
	public final static int MIN_TEXTURE_REGION_NUMBERS = 1;

	private Map<UI.Type, TextureRegion[]> assets;

	public UIAsset(GLGame glGame, String fileName, boolean isInAssets)
	{
		super(glGame, fileName, isInAssets);

	}

	public Map<UI.Type, TextureRegion[]> getAssets()
	{
		return assets;
	}

	public TextureRegion[] getAsset(UI.Type type) throws NullPointerException
	{
		TextureRegion[] tmp = assets.get(type);
		return tmp;
	}

}
