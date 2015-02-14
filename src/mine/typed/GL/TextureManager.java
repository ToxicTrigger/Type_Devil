package mine.typed.GL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


// make instance
// put TextureName and Texture Data 
public class TextureManager {
	
	private static volatile TextureManager me;
	public Map<String,Texture> textures;
	
	public static TextureManager getInstance(){
		if(me == null) me = new TextureManager();
		return me;
	}
	
	public TextureManager(){
		textures = new HashMap<String, Texture>();
	}
	
	public void reloadAll(){
		Iterator<String> iter = textures.keySet().iterator();
		Iterator<Texture> iterTex = textures.values().iterator();
		while(iter.hasNext()){
			Texture tex = iterTex.next();
			tex.reload();
		}
	}

	public void put(Texture tex){
		this.textures.put(tex.fileName, tex);
	}
	
	public Texture get(String name){
		return this.textures.get(name);
	}
	public Texture get(Texture tex){
		return this.textures.get(tex.fileName);
	}
	
	public void remove(String name){
		this.textures.remove(name);
	}
	public void remove(Texture tex){
		this.textures.remove(tex.fileName);
	}

}
