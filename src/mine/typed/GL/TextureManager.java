package mine.typed.GL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mine.typed.core.Tube;

// make instance
// put TextureName and Texture Data 
public class TextureManager {

    private static volatile TextureManager me;
    public Map<String, Texture> textures;

    public static TextureManager getInstance() {
	if (me == null)
	    me = new TextureManager();
	return me;
    }

    public TextureManager() {
	textures = new HashMap<String, Texture>();
    }

    public TextureManager(GLGame glgame, Tube... tubes) {
	textures = new HashMap<String, Texture>();
	for (int i = 0; i < tubes.length; i++) {
	    Texture tex = new Texture(glgame, (String) tubes[i].getY());
	    textures.put((String) tubes[i].getX(), tex);
	}
    }

    public void reloadAll() {
	Iterator<String> keys = textures.keySet().iterator();
	while (keys.hasNext()) {
	    textures.get(keys.next()).reload();
	}
    }

    /**
     * name = tex.filename
     * 
     * @param tex
     */
    public void put(Texture tex) {
	this.textures.put(tex.fileName, tex);
    }

    public Texture get(String name) {
	return this.textures.get(name);
    }

    public Texture get(Texture tex) {
	return this.textures.get(tex.fileName);
    }

    public void remove(String name) {
	this.textures.remove(name);
    }

    public void remove(Texture tex) {
	this.textures.remove(tex.fileName);
    }

}
