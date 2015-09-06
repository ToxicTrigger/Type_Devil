package mine.tools.res;

import java.util.HashMap;
import java.util.Iterator;

import mine.typed.GL.GLGame;
import mine.typed.GL.Texture;
import mine.typed.core.Tube;

public class TextureContainer{
	
	HashMap<String, Texture> texs;

	public TextureContainer(GLGame glgame, Tube ...tubes) {
		texs = new HashMap<String, Texture>();
		for(int i = 0; i < tubes.length; i++){
			Texture tex = new Texture(glgame, (String) tubes[i].getY());
			texs.put((String)tubes[i].getX(), tex);
		}
	}
	
	public Texture get(String name){
		return texs.get(name);
	}
	
	public void reloadAll(){
		Iterator<String> keys = texs.keySet().iterator();
		while(keys.hasNext()){
			texs.get(keys.next()).reload();
		}
	}

}
