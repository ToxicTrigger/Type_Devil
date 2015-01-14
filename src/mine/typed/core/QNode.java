package mine.typed.core;


/**
 * 큐를 구현 한 클래스 입니다. <p>
 * 
 * @author mrminer
 *
 */
public class QNode  {

	
	public final ID id;
	
	protected final Object mObject;
	protected int mInt;
	protected String mString;

	protected QNode next;

	/**
	 * 큐 노드를 생성 합니다.
	 * @param 해당 노드가 가질 데이터
	 */
	public QNode(Object obj){
		this.mObject = obj;
		this.next = null;
		id = new ID(this);
	}

	/**
	 * 인자값을 가지는 큐 노드를 생성 합니다.
	 * @param var
	 */
	public QNode(int var){
		this.mObject = var;
		this.mInt = var;
		this.next = null;
		id = new ID(this);
	}

	/**
	 * 인자값을 가지는 큐 노드를 생성 합니다.
	 * @param str
	 */
	public QNode(String str){
		this.mObject = str;
		this.mString = str;
		this.next = null;
		id = new ID(this);
	}

	/**
	 * 다음 큐 노드를 설정 합니다.
	 * @param 다음 큐 노드
	 */
	public void setNextQNode( QNode qnode ){
		this.next = qnode;
	}
	/**
	 * 다음 큐 노드를 가져옵니다.
	 * @return 다음 큐 노드
	 */
	public QNode getNextQNode(){
		return this.next;
	}

	/**
	 * 데이터를 가져옵니다.
	 * @return 데이터
	 */
	public Object getObject(){
		return this.mObject;
	}


}
