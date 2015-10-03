package mine.tools.debug;

import java.util.ArrayList;
import java.util.Iterator;

import mine.typed.GL.GLGame;
import mine.typed.GL.SpriteBatcher;
import mine.typed.GL.Texture;
import mine.typed.GL.TextureManager;
import mine.typed.GL.TextureRegion;
import mine.typed.core.Circle;
import mine.typed.core.Diagram;
import mine.typed.core.game.HitBox;

public class OverlapRenderer {
    /**
     * 디버깅 모드 인지의 여부를 가집니다.
     */
    public boolean isDebugging;

    public Texture OverlapTex;
    public TextureRegion overlap_w, overlap_r, overlap_b;
    public SpriteBatcher sb;

    private static OverlapRenderer me;

    public static OverlapRenderer getInstance(TypeLogger debug, GLGame glgame,
	    Texture overlapTex, SpriteBatcher sb) {
	if (me == null) {
	    me = new OverlapRenderer(debug, glgame, overlapTex, sb);
	    return me;
	} else {
	    return me;
	}

    }

    public OverlapRenderer(TypeLogger debug, GLGame glgame, Texture overlapTex,
	    SpriteBatcher sb) {
	this.isDebugging = debug.hasUseLogger();
	this.sb = sb;

	TextureManager.getInstance().put(overlapTex);
	OverlapTex = TextureManager.getInstance().get(overlapTex);
	overlap_w = new TextureRegion(OverlapTex, 256, 0, 256, 256);
	overlap_r = new TextureRegion(OverlapTex, 0, 256, 256, 256);
	overlap_b = new TextureRegion(OverlapTex, 0, 0, 256, 256);
    }

    public void drawBounds(Diagram... diagrams) {
	if (this.isDebugging) {
	    ArrayList<HitBox> rac = new ArrayList<HitBox>();
	    ArrayList<Circle> cir = new ArrayList<Circle>();

	    for (Diagram tmp : diagrams) {
		if (tmp instanceof Circle)
		    cir.add((Circle) tmp);
		else
		    rac.add((HitBox) tmp);
	    }

	    sb.beginBatch(OverlapTex);
	    Iterator<Circle> iterC = cir.iterator();
	    while (iterC.hasNext()) {
		Circle c = iterC.next();
		if (c.isOverlaped)
		    sb.drawSprite(c.center, c.radius, c.radius, this.overlap_r);
		else
		    sb.drawSprite(c.center, c.radius, c.radius, this.overlap_w);
	    }
	    sb.drawSprite(0, 0, 0.1f, 0.1f, this.overlap_b);

	    Iterator<HitBox> iterH = rac.iterator();
	    while (iterH.hasNext()) {
		HitBox h = iterH.next();
		if (h.getState() == HitBox.STATE_CHECK) {
		    if (h.isOverlaped)
			sb.drawSprite(h.lowerLeft.x + (h.width / 2), h.lowerLeft.y + (h.height / 2), h.width, h.height, overlap_r);
		    else
			sb.drawSprite(h.lowerLeft.x + (h.width / 2), h.lowerLeft.y + (h.height / 2), h.width, h.height, overlap_w);
		}
	    }
	    sb.endBatch();
	}
    }
}
