package mine.typed.core.game;

import java.util.HashMap;
import java.util.Iterator;

import mine.typed.core.Tube;
import android.annotation.SuppressLint;

/**
 * 해당 클래스는 다중 제어를 수행한다.
 * 
 * @author mrminer
 *
 */
@SuppressLint("UseSparseArrays")
public class FlagMaster
{

	/**
	 * Tube format = Completed, boolean[]
	 */
	public HashMap<Integer, Tube> flags;

	public FlagMaster()
	{
		flags = new HashMap<Integer, Tube>();
	}

	/**
	 * 
	 * @param name
	 * @param flags
	 * @return F = fail, T = suc
	 */
	public boolean put(int id, Tube flags)
	{
		if( this.flags.containsKey(id) ) return false;
		this.flags.put(id, flags);
		return true;
	}

	public boolean remove(int id)
	{
		Tube t = this.flags.remove(id);
		if( t != null ) return true;
		return false;
	}

	public Tube get(int id)
	{
		Tube get = this.flags.get(id);
		Tube result = new Tube(get.getX(), get.getY());
		return result;
	}

	public boolean putNewFlags(int id, boolean... flags)
	{
		Tube tmp = new Tube(false, flags);
		return this.put(id, tmp);
	}

	public boolean[] getFlag(int id)
	{
		boolean[] tmp = (boolean[]) this.get(id).getY();
		return tmp;
	}

	public void setFlag(int id, boolean Completed, boolean... flags)
	{
		boolean work = this.remove(id);
		if( !work ) return;
		Tube tmp = new Tube(Completed, flags);
		this.put(id, tmp);
	}

	public void cleanCompleted()
	{
		Iterator<Integer> keys = flags.keySet().iterator();
		while ( keys.hasNext() )
		{
			keys.next();
			if( (Boolean) this.flags.get(keys).getX() == true )
			{
				keys.remove();
				this.flags.remove(keys);
			}
		}
	}

	public boolean hasOver(int id)
	{
		return (Boolean) get(id).getX();
	}

}
