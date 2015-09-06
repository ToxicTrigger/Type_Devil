package mine.typed.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 재사용률을 상승시키기 위한 클래스
 * 
 * @author mrminer
 * 
 * @param <T>
 */
public class Pool<T>
{

	public final List<T> freeOBJ;
	/**
	 * 객체의 타입에 관여하지 않도록 하기 위해 만들어진 일종의 템플릿
	 */
	public final PoolObjectFactory<T> factory;
	public final int maxSize;

	/**
	 * 생성자
	 * 
	 * @param factory
	 *            저장할 객체의 타입.
	 * @param maxSize
	 *            최대 수용 가능한 객체의 갯수
	 */
	public Pool(final PoolObjectFactory<T> factory, final int maxSize)
	{

		this.factory = factory;
		this.maxSize = maxSize;
		this.freeOBJ = new ArrayList<T>(maxSize);
	}

	/**
	 * factory 의 타입에 의거한 객체를 생성한다.
	 * 
	 * @return 생성된 객체
	 */
	public T newOBJ()
	{

		T object = null;
		if( this.freeOBJ.size() == 0 )
		{
			object = this.factory.createObject();
		} else
		{
			object = this.freeOBJ.remove(this.freeOBJ.size() - 1);
		}

		return object;
	}

	/**
	 * 리스트의 빈공간을 채우는 매서드
	 * 
	 * @param object
	 *            채워질 객체
	 */
	public void free(final T object)
	{

		if( this.freeOBJ.size() < this.maxSize )
		{
			this.freeOBJ.add(object);
		}
	}

	/**
	 * 
	 * @author mrminer
	 * 
	 * @param <T>
	 */
	public interface PoolObjectFactory<T>
	{
		public T createObject();
	}

}
