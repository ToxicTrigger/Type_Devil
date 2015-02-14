package mine.tools.res;

import mine.typed.GL.TextureRegion;
import mine.typed.core.game.ui.UI;
import mine.typed.core.game.ui.UIAsset;

public class UIAssetManager {
	
	private static TextureRegion[] getWindow(UIAsset tex){
		TextureRegion[] texrs = new TextureRegion[UIAsset.MAX_TEXTURE_REGION_NUMBERS];
		//window					
		texrs[0] = new TextureRegion(tex, 0, 0, 32, 32);		// UpL
		texrs[1] = new TextureRegion(tex, 32, 0, 16, 32);		// Up
		texrs[2] = new TextureRegion(tex, 48, 0, 32, 32);		// UpR
		texrs[3] = new TextureRegion(tex, 0, 32, 32, 16);		// Lside
		texrs[4] = new TextureRegion(tex, 32, 32, 16, 16);		// centor
		texrs[5] = new TextureRegion(tex, 48, 32, 16, 32);		// Rside
		texrs[6] = new TextureRegion(tex, 0, 48, 32, 32);		// DownR
		texrs[7] = new TextureRegion(tex, 32, 48, 16, 32);		// Down
		texrs[8] = new TextureRegion(tex, 48, 48, 32, 32);		// DownL
		return texrs;
	}
	
	public static TextureRegion[] get(UIAsset tex, UI.Type type){
		switch(type){
		case Acc_Circle:
			return null;
			
		case Dif_Circle:
			return null;
			
		case Dis_Circle:
			return null;
			
		case Dot:
			TextureRegion[] dot = new TextureRegion[1];
			dot[0] = getDot(tex);
			return dot;
			
		case Menu:
			TextureRegion[] menu = new TextureRegion[1];
			menu[0] = getMenu(tex);
			return menu;

			
		case Off_Under_Switch:
			TextureRegion[] offunder = new TextureRegion[1];
			offunder[0] = getOffUnderBar(tex);
			return offunder;
			
		case On_Under_Switch:
			TextureRegion[] onunder = new TextureRegion[1];
			onunder[0] = getOnUnderBar(tex);
			return onunder;
			
		case ScrollBar:
			TextureRegion[] scroll = new TextureRegion[1];
			scroll[0] = getScrollBar(tex);
			return scroll;
			
		case War_Circle:
			return null;
			
		case Window:
			return getWindow(tex);
		case X:
			TextureRegion[] x = new TextureRegion[1];
			x[0] = getX(tex);
			return x;
			
		default:
			return null;
		
		}
	}
	
	private static TextureRegion getX(UIAsset tex){
		TextureRegion x = new TextureRegion(tex , 80, 0 , 16 , 16);
		return x;
	}
	private static TextureRegion getMenu(UIAsset tex){
		TextureRegion menu = new TextureRegion(tex , 112, 0 , 32 , 16);
		return menu;
	}
	private static TextureRegion getScrollBar(UIAsset tex){
		TextureRegion scr = new TextureRegion(tex , 144, 0 , 16 , 16);
		return scr;
	}
	private static TextureRegion getOffUnderBar(UIAsset tex){
		TextureRegion x = new TextureRegion(tex , 160, 0 , 16 , 16);
		return x;
	}
	private static TextureRegion getOnUnderBar(UIAsset tex){
		TextureRegion x = new TextureRegion(tex , 176, 0 , 16 , 16);
		return x;
	}
	private static TextureRegion getDot(UIAsset tex){
		TextureRegion x = new TextureRegion(tex , 192, 0 , 16 , 16);
		return x;
	}

}
