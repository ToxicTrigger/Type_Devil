package mine.typed.core;

import java.util.ArrayList;
import java.util.List;

import mine.typed.core.game.GameObject;
import android.util.FloatMath;

/**
 * 해당 클래스는 화면을 여러개의 셀로 나누고 그 셀에 포함된 것들만 계산하도록 해주는 클래스 입니다. 충돌처리등 비교적 연산비용이 큰 것들을
 * 보다 저렴하고 효과적으로 관리할수 있습니다.
 * 
 * @author mrminer
 *
 */
@SuppressWarnings("deprecation")
public class SpatialHashGrid
{
	List<GameObject>[] dynamicCells;
	List<GameObject>[] staticCells;
	int cellsPerRow;
	int cellsPerCol;
	float cellSize;
	int[] cellIds = new int[ 4 ];
	List<GameObject> foundObjects;

	@SuppressWarnings({ "unchecked" })
	public SpatialHashGrid(final float worldWidth, final float worldHeight, final float cellSize)
	{

		this.cellSize = cellSize;
		this.cellsPerRow = (int) FloatMath.ceil(worldWidth / cellSize);
		this.cellsPerCol = (int) FloatMath.ceil(worldHeight / cellSize);
		final int numCells = this.cellsPerRow * this.cellsPerCol;
		this.dynamicCells = new List[ numCells ];
		this.staticCells = new List[ numCells ];
		for ( int i = 0; i < numCells; i++ )
		{
			this.dynamicCells[ i ] = new ArrayList<GameObject>(10);
			this.staticCells[ i ] = new ArrayList<GameObject>(10);
		}
		this.foundObjects = new ArrayList<GameObject>(10);
	}

	/**
	 * 고정되어 있지만 동적으로 위치가 변경가능한 obj 를 추가합니다.
	 * 
	 * @param obj
	 */
	public void insertStaticObject(final GameObject obj)
	{

		final int[] cellIds = this.getCellIds(obj);
		int i = 0;
		int cellId = -1;
		while ( (i <= 3) && ((cellId = cellIds[ i++ ]) != -1) )
		{
			this.staticCells[ cellId ].add(obj);
		}
	}

	/**
	 * 움직일수 있는 obj 를 추가합니다.
	 * 
	 * @param obj
	 */
	public void insertDynamicObject(final GameObject obj)
	{
		final int[] cellIds = this.getCellIds(obj);
		int i = 0;
		int cellId = -1;
		while ( (i <= 3) && ((cellId = cellIds[ i++ ]) != -1) )
		{
			this.dynamicCells[ cellId ].add(obj);
		}
	}

	/**
	 * 셀에서 인자값으로 들어온 obj를 삭제합니다.
	 * 
	 * @param obj
	 */
	public void removeObject(final GameObject obj)
	{

		final int[] cellIds = this.getCellIds(obj);
		int i = 0;
		int cellId = -1;
		while ( (i <= 3) && ((cellId = cellIds[ i++ ]) != -1) )
		{
			this.dynamicCells[ cellId ].remove(obj);
			this.staticCells[ cellId ].remove(obj);
		}
	}

	/**
	 * 셀을 정리합니다.
	 * 
	 * @param obj
	 */
	public void clearDynamicCells(final GameObject obj)
	{

		final int len = this.dynamicCells.length;
		for ( int i = 0; i < len; i++ )
		{
			this.dynamicCells[ i ].clear();
		}
	}

	public List<GameObject> getPotentialColliders(final GameObject obj)
	{

		this.foundObjects.clear();
		final int[] cellIds = this.getCellIds(obj);
		int i = 0;
		int cellId = -1;
		while ( (i <= 3) && ((cellId = cellIds[ i++ ]) != -1) )
		{
			int len = this.dynamicCells[ cellId ].size();
			for ( int j = 0; j < len; j++ )
			{
				final GameObject collider = this.dynamicCells[ cellId ].get(j);
				if( !this.foundObjects.contains(collider) )
				{
					this.foundObjects.add(collider);
				}
			}

			len = this.staticCells[ cellId ].size();
			for ( int j = 0; j < len; j++ )
			{
				final GameObject collider = this.staticCells[ cellId ].get(j);
				if( !this.foundObjects.contains(collider) )
				{
					this.foundObjects.add(collider);
				}
			}
		}
		return this.foundObjects;
	}

	/**
	 * 인자값의 obj 의 정보를 불러옵니다.
	 * 
	 * @param obj
	 * @return
	 */
	public int[] getCellIds(final GameObject obj)
	{

		final int x1 = (int) FloatMath.floor(obj.bounds.lowerLeft.x / this.cellSize);
		final int y1 = (int) FloatMath.floor(obj.bounds.lowerLeft.y / this.cellSize);
		final int x2 = (int) FloatMath.floor((obj.bounds.lowerLeft.x + obj.bounds.width) / this.cellSize);
		final int y2 = (int) FloatMath.floor((obj.bounds.lowerLeft.y + obj.bounds.height) / this.cellSize);

		if( (x1 == x2) && (y1 == y2) )
		{
			if( (x1 >= 0) && (x1 < this.cellsPerRow) && (y1 >= 0) && (y1 < this.cellsPerCol) )
			{
				this.cellIds[ 0 ] = x1 + (y1 * this.cellsPerRow);
			} else
			{
				this.cellIds[ 0 ] = -1;
			}
			this.cellIds[ 1 ] = -1;
			this.cellIds[ 2 ] = -1;
			this.cellIds[ 3 ] = -1;
		} else if( x1 == x2 )
		{
			int i = 0;
			if( (x1 >= 0) && (x1 < this.cellsPerRow) )
			{
				if( (y1 >= 0) && (y1 < this.cellsPerCol) )
				{
					this.cellIds[ i++ ] = x1 + (y1 * this.cellsPerRow);
				}
				if( (y2 >= 0) && (y2 < this.cellsPerCol) )
				{
					this.cellIds[ i++ ] = x1 + (y2 * this.cellsPerRow);
				}
			}
			while ( i <= 3 )
			{
				this.cellIds[ i++ ] = -1;
			}
		} else if( y1 == y2 )
		{
			int i = 0;
			if( (y1 >= 0) && (y1 < this.cellsPerCol) )
			{
				if( (x1 >= 0) && (x1 < this.cellsPerRow) )
				{
					this.cellIds[ i++ ] = x1 + (y1 * this.cellsPerRow);
				}
				if( (x2 >= 0) && (x2 < this.cellsPerRow) )
				{
					this.cellIds[ i++ ] = x2 + (y1 * this.cellsPerRow);
				}
			}
			while ( i <= 3 )
			{
				this.cellIds[ i++ ] = -1;
			}
		} else
		{
			int i = 0;
			final int y1CellsPerRow = y1 * this.cellsPerRow;
			final int y2CellsPerRow = y2 * this.cellsPerRow;
			if( (x1 >= 0) && (x1 < this.cellsPerRow) && (y1 >= 0) && (y1 < this.cellsPerCol) )
			{
				this.cellIds[ i++ ] = x1 + y1CellsPerRow;
			}
			if( (x2 >= 0) && (x2 < this.cellsPerRow) && (y1 >= 0) && (y1 < this.cellsPerCol) )
			{
				this.cellIds[ i++ ] = x2 + y1CellsPerRow;
			}
			if( (x2 >= 0) && (x2 < this.cellsPerRow) && (y2 >= 0) && (y2 < this.cellsPerCol) )
			{
				this.cellIds[ i++ ] = x2 + y2CellsPerRow;
			}
			if( (x1 >= 0) && (x1 < this.cellsPerRow) && (y2 >= 0) && (y2 < this.cellsPerCol) )
			{
				this.cellIds[ i++ ] = x1 + y2CellsPerRow;
			}
			while ( i <= 3 )
			{
				this.cellIds[ i++ ] = -1;
			}
		}
		return this.cellIds;
	}
}