package mine.typed.GL.Test;

import mine.typed.net.devil.Devil;



public class Main{

	public static void main(String[] arg){
		
		for(int i = 0; i < 10; i++)
		joinHell();

	}
	
	public static void joinHell(){
		Devil c = new Devil("localhost", 4444);
	}

}
