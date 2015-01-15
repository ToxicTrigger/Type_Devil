package mine.typed.GL.Test;

import mine.typed.core.Circle;
import mine.typed.core.OverlapTester;
import mine.typed.core.Rectangle;
import mine.typed.core.V2;
import mine.typed.core.game.HitBox;



public class Main{

	public static void main(String[] arg){
		
		Circle c = new Circle(new V2(0 , 0), 10);
		Rectangle r = new Rectangle(new V2(0 , 2), 10, 10);
		HitBox h = new HitBox(5, 0, 10, 10);
		
		System.out.println(OverlapTester.overlap(r, h));
		
	}

}
