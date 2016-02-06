package mine.typed.core.game;

import java.util.HashMap;

import mine.typed.GL.Animation;
import mine.typed.GL.Texture;

public class DrawData {

    private Texture tex;
    private HashMap<Integer, Animation> anis;

    public static class Builder {

	public class aniadd {
	    private HashMap<Integer, Animation> anis;
	    private Texture Tex;

	    private aniadd(Texture tex) {
		Tex = tex;
	    }

	    public Done addAnimation(Animation... Anis) {
		int key = 0;
		for (Animation tmp : Anis) {
		    this.anis.put(key, tmp);
		    key++;
		}
		return new Done(Tex, anis);
	    }

	    public class Done {
		private Texture tex;
		private HashMap<Integer, Animation> anis;

		private Done(Texture Tex, HashMap<Integer, Animation> anis) {
		    this.anis = anis;
		    tex = Tex;
		}

		public DrawData build() {
		    return new DrawData(tex, anis);
		}
	    }

	}

	public aniadd setTexture(Texture Tex) {
	    return new aniadd(Tex);
	}
    }

    public DrawData(Texture tex2, HashMap<Integer, Animation> anis2) {
	this.anis = anis2;
	this.tex = tex2;
    }

    public Texture getTexture() {
	return tex;
    }

    public HashMap<Integer, Animation> getAnimations() {
	return anis;
    }
}
