package mine.typed.core.game.state;

import java.util.ArrayList;

//read state and run event
public class StatusMachine
{

	private ArrayList<Status> status;
	private boolean isLocked;

	public StatusMachine()
	{
		status = new ArrayList<Status>();
	}

	public boolean hasLocked()
	{
		return isLocked;
	}

	public void setLock(boolean lock)
	{
		isLocked = lock;
	}

	public void put(Status status)
	{
		if( !isLocked )
		{
			this.status.add(status);
		}
	}

	public Status pop()
	{
		if( !isLocked )
		{
			int cusor = this.status.size() - 1;

			Status s = this.status.get(cusor);
			this.status.remove(cusor);

			return s;
		}
		return null;
	}

	public void relese()
	{
		status.clear();
	}

}
