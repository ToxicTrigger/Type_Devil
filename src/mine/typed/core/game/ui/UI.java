package mine.typed.core.game.ui;

import mine.typed.core.V2;

/**
 * all ui are have 9 TextureRegion UpL , Lside , UpR , UpBar, Centor, DownL ,
 * Rside , DownR , DownBar
 * 
 * never change List UpL , UpR, DownL, DownR
 * 
 * side change List UpBar , DownBar
 * 
 * up and down List Lside , Rside
 * 
 * all accept centor
 * 
 * @author mrminer
 *
 */
public class UI extends mine.typed.core.game.DynamicGameObject {

    public enum ResType { // TextrueRegions

	Window, // 9

	Up_Dis_Button, // 9
	Up_Acc_Button, // 9
	Up_War_Button, // 9
	Up_Dif_Button, // 9

	Down_Dis_Button, // 9
	Down_Acc_Button, // 9
	Down_War_Button, // 9
	Down_Dif_Button, // 9

	Dis_Circle, // 1
	Acc_Circle, // 1
	War_Circle, // 1
	Dif_Circle, // 1

	X, // 1
	Menu, // 1
	ScrollBar, // 1
	Off_Under_Switch, // 1
	On_Under_Switch, // 1
	Dot, // 1

	UnDefine

    }

    public enum Type {
	Window,
	Dis_Button,
	Acc_Button,
	Dif_Button,
	War_Button,
	Dis_Circle,
	Acc_Circle,
	Dif_Circle,
	War_Circle,

	X,
	Menu,
	ScrollBar,
	Off_Under_Switch,
	On_Under_Switch,
	Dot,

	UnDefine
    }

    public enum Effect {
	Slide, // if isUpEffectDone false? Left to Centor else Centor to Right.
	Drop, // if isUpEffectDone false? Up Edge to Centor else Centor to Down
	      // Edge.
	Up, // if isUpEffectDone false? Donw Edge to Centor else Centor to Up
	    // Edge.
	Fade, // if isUpEffectDone false? fade-in else fade-out.
	Custom
    }

    public ResType UpRes, DownRes, DifRes;
    public final Type type;
    public Effect UpEffect, DownEffect;
    public boolean isShow;
    public boolean isUpEffectDone, isDownEffectDone, isCentorShowOver;
    public V2 startPos, CentorPos, EndPos;
    public float alpha;
    public float showTime;

    public UI(V2 Start, V2 Centor, V2 End, float width, float height,
	    Type Type, Effect up, Effect down, float showTime) {
	super(Start.x, Start.y, width, height);
	this.type = Type;
	setResType();
	this.UpEffect = up;
	this.DownEffect = down;
	this.startPos = Start;
	this.EndPos = End;
	this.CentorPos = Centor;
	isShow = true;
	this.showTime = showTime;
	this.accel.set(9.6f, 9.6f);
    }

    public void move(float del) {

	if (isUpEffectDone) {
	    if (showTime <= 0)
		showTime -= del;
	}

	if (isCentorShowOver & isUpEffectDone) {
	    this.runDownEffect();
	} else if (isCentorShowOver & isUpEffectDone & showTime <= 0) {
	    this.runDownEffect();
	}

	if (!isUpEffectDone)
	    this.runUpEffect();

    }

    private void runUpEffect() {
	if (this.UpEffect != Effect.Fade) {
	    if (!this.isUpEffectDone) {
		if (this.position.x <= this.CentorPos.x & this.position.y >= this.CentorPos.y) {
		    switch (UpEffect) {
		    case Custom:
			break;
		    case Drop:
			this.position.y -= (position.y % this.CentorPos.y) * this.accel.y;
			break;
		    case Fade:
			break;
		    case Slide:
			this.position.x += (position.x % this.CentorPos.x) * this.accel.x;
		    case Up:
			this.position.y += (position.y % this.CentorPos.y) * this.accel.y;
			break;
		    }
		} else {
		    this.isUpEffectDone = true;
		}
	    }
	} else {
	    if (alpha > 0.0f) {
		this.alpha -= this.accel.x / 100f;
	    } else {
		this.isUpEffectDone = true;
	    }

	}

    }

    private void runDownEffect() {
	if (this.UpEffect != Effect.Fade) {
	    if (!this.isDownEffectDone) {
		if (this.position.x <= this.EndPos.x & this.position.y >= this.EndPos.y) {
		    switch (UpEffect) {
		    case Custom:
			break;
		    case Drop:
			this.position.y -= (position.y % this.EndPos.y) * this.accel.y;
			break;
		    case Fade:
			break;
		    case Slide:
			this.position.x += (position.x % this.EndPos.x) * this.accel.x;
		    case Up:
			this.position.y += (position.y % this.EndPos.y) * this.accel.y;
			break;
		    }
		} else {
		    this.isDownEffectDone = true;
		}
	    }
	} else {
	    if (alpha < 1.0f) {
		this.alpha += this.accel.x / 100f;
	    } else {
		this.isDownEffectDone = true;
	    }

	}

    }

    public boolean hasShow() {
	return this.isShow;
    }

    private void setResType() {
	switch (type) {
	case Acc_Button:
	    this.UpRes = ResType.Up_Acc_Button;
	    this.DownRes = ResType.Down_Acc_Button;
	    this.DifRes = ResType.Up_Dif_Button;
	    return;
	case Acc_Circle:
	    this.DifRes = ResType.Acc_Circle;
	    return;
	case Dif_Button:
	    this.DifRes = ResType.Up_Dif_Button;
	    this.UpRes = ResType.Up_Dif_Button;
	    this.DifRes = ResType.Down_Dif_Button;
	    return;
	case Dif_Circle:
	    this.DifRes = ResType.Dif_Circle;
	    return;
	case Dis_Button:
	    this.UpRes = ResType.Up_Dis_Button;
	    this.DownRes = ResType.Down_Dis_Button;
	    this.DifRes = ResType.Up_Dif_Button;
	    return;
	case Dis_Circle:
	    this.DifRes = ResType.Dis_Circle;
	    return;
	case Dot:
	    this.DifRes = ResType.Dot;
	    return;
	case Menu:
	    this.DifRes = ResType.Menu;
	    return;
	case Off_Under_Switch:
	    this.DifRes = ResType.Off_Under_Switch;
	    return;
	case On_Under_Switch:
	    this.DifRes = ResType.On_Under_Switch;
	    return;
	case ScrollBar:
	    this.DifRes = ResType.ScrollBar;
	    return;
	case UnDefine:
	    this.DifRes = ResType.UnDefine;
	    return;
	case War_Button:
	    this.UpRes = ResType.Up_War_Button;
	    this.DifRes = ResType.Up_Dif_Button;
	    this.DownRes = ResType.Down_War_Button;
	    return;
	case War_Circle:
	    this.DifRes = ResType.War_Circle;
	    return;
	case Window:
	    this.DifRes = ResType.Window;
	    return;
	case X:
	    this.DifRes = ResType.X;
	    return;
	default:
	    this.DifRes = ResType.UnDefine;
	    return;

	}
    }

}
