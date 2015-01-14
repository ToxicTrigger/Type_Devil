package mine.typed.core;

/**
 * 두개의 점으로 이루어진 선 입니다.
 * @author mrminer
 *
 */
public class Line {
	public V2 start , end;
	public float dist;


	/**
	 * 선을 생성 합니다.
	 * 
	 * @param 시작 점의 위치
	 * @param 끝 점의 위치
	 */
	public Line(V2 Start , V2 End){
		this.start = Start;
		this.end = End;
		this.dist = this.start.dist( this.end );

	}

	/**
	 * 선을 생성 합니다.
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public Line(float startX , float startY , float endX , float endY){
		this.start = new V2(startX , startY);
		this.end = new V2(endX , endY);
		this.dist = this.start.dist( this.end );
	}

}
