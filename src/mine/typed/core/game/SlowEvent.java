package mine.typed.core.game;

public abstract class SlowEvent extends Thread
{
	private Object[] objs;

	public SlowEvent(Object... objs)
	{
		this.objs = objs;

	}

	@Override
	public void run()
	{
		toDo(objs);
	}

	public abstract double toDo(Object... objs);

}
