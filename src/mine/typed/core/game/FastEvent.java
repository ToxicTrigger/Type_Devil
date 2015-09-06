package mine.typed.core.game;

public abstract class FastEvent extends Thread {
	
	private Object[] objs;
	
	
	public FastEvent(Object ... objs){
		this.objs = objs;
		super.start();
	}

	
	@Override
	public void run(){
		toDo(objs);
	}

	public abstract double toDo(Object ... objs);
	
}
