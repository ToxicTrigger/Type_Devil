package mine.typed.core;

/**
 * QNode 를 사용한 큐 구현체 입니다.
 * 
 * @author mrminer
 *
 */
public class Q
{
	@Override
	public String toString()
	{
		return "Q [head=" + this.head + ", last=" + this.last + ", max=" + this.max + ", count=" + this.count + "]";
	}

	public QNode head, last;
	/**
	 * 최대 저장 가능한 큐의 갯수.
	 * <p>
	 * 기본적으로 999999 개의 큐를 저장 가능
	 */
	public final int max;
	private int count;

	/**
	 * 큐를 생성 합니다.
	 * <p>
	 * head 와 last 큐 노드를 null 로 생성 합니다.
	 * <p>
	 * 
	 * @see <code>		
	 * this.head = null; <p>
	 * 	this.last = null;
	 * 	</code>
	 */
	public Q()
	{
		this.head = null;
		this.last = null;
		this.max = 999999;
	}

	/**
	 * 큐를 생성 합니다.
	 * 
	 * @param Head
	 * @param Last
	 */
	public Q(QNode Head, QNode Last)
	{
		this.head = Head;
		this.last = Last;
		this.max = 999999;
	}

	/**
	 * 최대 생성 가능한 수를 제한하여 생성 합니다.
	 * 
	 * @param max
	 */
	public Q(int max)
	{
		this.max = max;
		this.head = null;
		this.last = null;
	}

	/**
	 * 최대 생성 가능한 수를 제한하여 생성 합니다.
	 * 
	 * @param Head
	 * @param Last
	 * @param max
	 */
	public Q(QNode Head, QNode Last, int max)
	{
		this.head = Head;
		this.last = Last;
		this.max = max;
	}

	/**
	 * 다른 큐를 복사 하여 생성 합니다.
	 * 
	 * @param q
	 */
	public Q(Q q)
	{
		this.head = q.head;
		this.last = q.last;
		this.max = q.max;
	}

	/**
	 * head 가 null 이라면 head 와 last 를 obj 로 설정 합니다.
	 * <p>
	 * 그렇지 않다면 last 의 다음 큐 노드를 obj 로 설정하고 , last 를 obj 로 변경 합니다.
	 * 
	 * @param obj
	 */
	public void add(Object obj)
	{
		if( this.count < this.max )
		{
			final QNode tmp = new QNode(obj);
			this.count++;
			if( this.head == null )
			{
				this.head = this.last = tmp;
			} else
			{
				this.last.setNextQNode(tmp);
				this.last = tmp;
			}
		} else
		{
			return;
		}
	}

	/**
	 * 큐의 데이터를 가져 옵니다. 단 head 가 null 이라면 null 을 리턴 합니다.
	 * <p>
	 * 그렇지 않다면 head 의 데이터를 리턴 합니다.
	 * <p>
	 * 그후 head 를 다음 큐 노드로 설정하고 종료 합니다.
	 * 
	 * @return
	 */
	public Object get()
	{
		Object tmp;
		if( this.head == null ) return null;

		tmp = this.head.getObject();
		this.head = this.head.getNextQNode();
		this.count--;
		return tmp;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.count;
		result = (prime * result) + ((this.head == null) ? 0 : this.head.hashCode());
		result = (prime * result) + ((this.last == null) ? 0 : this.last.hashCode());
		result = (prime * result) + this.max;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( !(obj instanceof Q) ) return false;
		final Q other = (Q) obj;
		if( this.count != other.count ) return false;
		if( this.head == null )
		{
			if( other.head != null ) return false;
		} else if( !this.head.equals(other.head) ) return false;
		if( this.last == null )
		{
			if( other.last != null ) return false;
		} else if( !this.last.equals(other.last) ) return false;
		if( this.max != other.max ) return false;
		return true;
	}

}
