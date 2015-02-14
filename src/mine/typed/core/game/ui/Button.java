package mine.typed.core.game.ui;

import mine.typed.core.OverlapTester;
import mine.typed.core.V2;
import mine.typed.core.interfaces.Input.TouchEvent;

/**
 * 버튼의 구현체
 * @author mrminer
 *
 */
public abstract class Button extends UI {	
	public static final int DISABLED = 0;
	public static final int ACTIVATION = 1;
	public static final int PUSHING = 2;

	public V2 pos;

	public int buttonState;

	/**
	 * 버튼을 생성 합니다.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Button( float x , float y , float width , float height, UI.Type type ) {
		super(new V2(x, y), new V2(x, y), new V2(x, y), width, height, type, UI.Effect.Custom, UI.Effect.Custom, 999999);
		this.pos = new V2(this.hitbox.lowerLeft.x + (this.hitbox.width / 2) ,this.hitbox.lowerLeft.y + (this.hitbox.height / 2));
		
		this.buttonState = 0;
	}
	/**
	 * 버튼을 생성 합니다.
	 * @param v
	 * @param width
	 * @param height
	 */
	public Button(V2 v , float width , float height, UI.Type type  ) {
		super( v, v, v , width , height , type, UI.Effect.Custom, UI.Effect.Custom, 99999);
		this.pos = new V2(this.hitbox.lowerLeft.x + (this.hitbox.width / 2) ,this.hitbox.lowerLeft.y + (this.hitbox.height / 2));
		buttonState = 0;
	}
	
	public Button( float x , float y , float width , float height) {
		super( new V2(x, y), new V2(x, y), new V2(x, y) , width , height , UI.Type.UnDefine , UI.Effect.Custom, UI.Effect.Custom, 99999);
		this.pos = new V2(this.hitbox.lowerLeft.x + (this.hitbox.width / 2) ,this.hitbox.lowerLeft.y + (this.hitbox.height / 2));
		
		this.buttonState = 0;
	}
	
	public Button(V2 v , float width , float height ) {
		super( v, v, v , width , height , UI.Type.UnDefine, UI.Effect.Custom, UI.Effect.Custom, 99999);
		this.pos = new V2(this.hitbox.lowerLeft.x + (this.hitbox.width / 2) ,this.hitbox.lowerLeft.y + (this.hitbox.height / 2));
		buttonState = 0;
	}

	protected abstract void Event();
	
	public void updateButtonState(int touchType , V2 touchPos){

		//1.pos in
		//2 down			PUSHING 	DIS
		//3 keep pos in
		//4 up				UP			ACT
		//5 up other pos	UP			DIS
		if(OverlapTester.pointInRectangle(this.hitbox, touchPos)){
			if( touchType == TouchEvent.TOUCH_DOWN | touchType == TouchEvent.TOUCH_DRAGGED) this.buttonState = Button.PUSHING;
		}else if(!OverlapTester.pointInRectangle(this.hitbox, touchPos) & touchType == TouchEvent.TOUCH_UP){
			this.buttonState = Button.DISABLED;
			return;
		}
		
		if((OverlapTester.pointInRectangle(this.hitbox, touchPos) & this.buttonState == Button.PUSHING) & touchType == TouchEvent.TOUCH_UP){
			this.buttonState = Button.ACTIVATION; 
		}
		
		if(!OverlapTester.pointInRectangle(this.hitbox, touchPos) & 
		(touchType == TouchEvent.TOUCH_UP | touchType == TouchEvent.TOUCH_DOWN | touchType == TouchEvent.TOUCH_DRAGGED)) 
			{ this.buttonState = Button.DISABLED; }
		

	}
	
	/**
	 * 자동으로 상태를 업데이트하며 이벤트를 동작시킵니다. 
	 * @param event
	 */
	public void autoRunEvent(int eventType , V2 touchPos){
		int type = eventType;
		V2 pos = touchPos;
		this.updateButtonState(type, pos);
		runEvent();
		if(this.buttonState == Button.ACTIVATION | (!OverlapTester.pointInRectangle(this.hitbox, touchPos) & eventType == TouchEvent.TOUCH_UP))
			this.buttonState = Button.DISABLED;
	}
	
	public void runEvent(){
		if(this.buttonState == Button.ACTIVATION){
			this.Event();
		}
	}
		
	public int getButtonState() {
		return buttonState;
	}
	public void setButtonState(int buttonState) {
		this.buttonState = buttonState;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + buttonState;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Button)) {
			return false;
		}
		Button other = (Button) obj;
		if (buttonState != other.buttonState) {
			return false;
		}
		if (pos == null) {
			if (other.pos != null) {
				return false;
			}
		} else if (!pos.equals(other.pos)) {
			return false;
		}
		return true;
	}

}
