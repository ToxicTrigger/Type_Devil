package mine.typed.core.game.map;

import mine.typed.core.V2;
import mine.typed.core.game.GameObject;

/**
 * 2D 타일의 구현체
 * 
 * @author mrminer
 *
 */
public class Tile2D extends GameObject {

    /**
     * 통과 가능 여부
     * <p>
     * 기본 값은 true
     */
    public boolean pass = true;
    /**
     * 타일의 이름
     * <p>
     */
    public String TileType;

    /**
     * 2D 타일을 생성 합니다.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param TileType
     *            다른 타일과 구분이 가능하도록 하는 제 2의 정의 입니다.
     */
    public Tile2D(float x, float y, float width, float height, String TileType) {

	super(x, y, width, height);
	this.TileType = TileType;
    }

    /**
     * 2D 타일을 생성 합니다.
     * 
     * @param v
     * @param width
     * @param height
     * @param TileType
     *            다른 타일과 구분이 가능하도록 하는 제 2의 정의 입니다.
     */
    public Tile2D(V2 v, float width, float height, String TileType) {

	super(v.x, v.y, width, height);
	this.TileType = TileType;

    }

    /**
     * 2D 타일을 생성 합니다.
     * 
     * @param x
     * @param y
     * @param r
     * @param TileType
     */
    public Tile2D(float x, float y, float r, String TileType) {

	super(x, y, r);
	this.TileType = TileType;
    }

    /**
     * 2D 타일을 생성 합니다.
     * 
     * @param v
     * @param r
     * @param TileType
     */
    public Tile2D(V2 v, float r, String TileType) {

	super(v.x, v.y, r);
	this.TileType = TileType;
    }

    /**
     * 물체가 해당 물체를 지나갈수 있는지의 여부를 설정.
     * 
     * @param passed
     *            = true 지나갈수 있음. passed = false 지나갈수 없음.
     */
    public void setPassable(boolean passed) {
	this.pass = passed;
    }

    @Override
    public String toString() {
	return "Tile2D [TileType=" + this.TileType + ", pass=" + this.pass + ", x=" + this.position.x + ", y=" + this.position.y + ", w=" + this.bounds.width + ", h=" + this.bounds.height + "]\n";
    }

}
