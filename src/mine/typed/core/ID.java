package mine.typed.core;

import mine.typed.core.game.GameObject;

public class ID {
	
	private final long ID;
	private final long generatedTime = System.nanoTime();
	
	public ID(Object obj){
		ID = ( (generatedTime - System.nanoTime()) - obj.toString().hashCode() * obj.hashCode() );
	}
	
	public ID(GameObject gobj){
		ID = ( (generatedTime - System.nanoTime()) - gobj.toString().hashCode() * gobj.hashCode() );
	}
	
	public double getIDcode(){
		return ID;
	}

	public long getGeneratedTime() {
		return generatedTime;
	}
	
}
