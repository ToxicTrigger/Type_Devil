package mine.typed.core.game;

import mine.typed.core.V2;

public class Text extends DynamicGameObject {
    public final String text;
    private float fontSize;
    private final int textLen;
    
    public Text(V2 v, float FontSize, String Text) {
	super(v, FontSize * Text.length(), FontSize);
	text = Text;
	fontSize = FontSize;
	textLen = Text.length();
	
	float reX, reY;
	reX = this.hitbox.lowerLeft.x + ((fontSize * textLen) / 2);
	reY = this.hitbox.lowerLeft.y + (fontSize / 2);
	this.position.set(reX, reY);
    }
    
    public float getFontSize(){
	return this.fontSize;
    }
    
    public void setFontSize(float Size){
	fontSize = Size;
	float reX, reY;
	reX = this.hitbox.lowerLeft.x + ((fontSize * textLen) / 2);
	reY = this.hitbox.lowerLeft.y + (fontSize / 2);
	this.position.set(reX, reY);
    }
    
    public V2 getPosition(){
	return this.position;
    }
    
    public String getText(){
	return text;
    }

}
