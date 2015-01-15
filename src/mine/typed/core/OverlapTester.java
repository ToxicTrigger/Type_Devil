
package mine.typed.core;

import mine.typed.core.game.HitBox;

/**
 * 원과 사각형 , 구의 충돌을 처리하기 위한 클래스
 * 
 * @author mrminer
 * 
 */
public class OverlapTester {
	
	
	public static boolean overlap(Diagram a, Diagram b){
		Circle cirA = null;
		Rectangle rectA = null;
		if( a instanceof Circle ){
			cirA = (Circle) a;
		}else{
			rectA = (Rectangle) a;
		}
		Circle cirB = null;
		Rectangle rectB = null;
		if( b instanceof Circle ){
			cirB = (Circle) b;
		}else{
			rectB = (Rectangle) b;
		}
		
		boolean overlaped = false;
		if(cirA == null & cirB == null){
			overlaped = overlapRectangles(rectA, rectB);
			return overlaped;
		}
		if(cirA != null & cirB != null){
			overlaped = overlapCircles(cirA, cirB);
			return overlaped;
		}
		if(cirA == null & cirB != null){
			overlaped = overlapCircleRectangle(cirB, rectA);
			return overlaped;
		}
		if(cirA != null & cirB == null){
			overlaped = overlapCircleRectangle(cirA, rectB);
			return overlaped;
		}
		
		return overlaped;
		
	}
	
	
	/**
	 * 두 원의 충돌 여부를 확인
	 * 
	 * @param c1
	 * @param c2
	 * @return 충돌시 true, 아닐시 false
	 */
	public static boolean overlapCircles(final Circle c1, final Circle c2) {

		final float distance = c1.center.distSquared( c2.center );
		final float radiusSum = c1.radius + c2.radius;
		return distance <= (radiusSum * radiusSum);
	}
	/**
	 * 두 사각형의 충돌 여부를 확인
	 * 
	 * @param r1
	 * @param r2
	 * @return 충돌시 true, 아닐시 false
	 */
	public static boolean overlapRectangles(final Rectangle r1, final Rectangle r2) {

		if ( (r1.lowerLeft.x < (r2.lowerLeft.x + r2.width))
				&& ((r1.lowerLeft.x + r1.width) > r2.lowerLeft.x)
				&& (r1.lowerLeft.y < (r2.lowerLeft.y + r2.height))
				&& ((r1.lowerLeft.y + r1.height) > r2.lowerLeft.y) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * HitBox 에 대응하는 충돌 체크 메서드 입니다.
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static boolean overlapHitBoxs(final HitBox h1, final HitBox h2) {

		if ( (h1.lowerLeft.x < (h2.lowerLeft.x + h2.width))
				&& ((h1.lowerLeft.x + h1.width) > h2.lowerLeft.x)
				&& (h1.lowerLeft.y < (h2.lowerLeft.y + h2.height))
				&& ((h1.lowerLeft.y + h1.height) > h2.lowerLeft.y) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean overlapHitBoxAndRectangle(final HitBox h, final Rectangle r) {

		if ( (h.lowerLeft.x < (h.lowerLeft.x + h.width))
				&& ((h.lowerLeft.x + h.width) > h.lowerLeft.x)
				&& (h.lowerLeft.y < (h.lowerLeft.y + h.height))
				&& ((h.lowerLeft.y + h.height) > h.lowerLeft.y) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 원과 사각형의 충돌 여부를 확인
	 * 
	 * @param c
	 * @param r
	 * @return 충돌시 true, 아닐시 false
	 */
	public static boolean overlapCircleRectangle(final Circle c, final Rectangle r) {

		float closestX = c.center.x;
		float closestY = c.center.y;

		if ( c.center.x < r.lowerLeft.x ) {
			closestX = r.lowerLeft.x;
		} else if ( c.center.x > (r.lowerLeft.x + r.width) ) {
			closestX = r.lowerLeft.x + r.width;
		}

		if ( c.center.y < r.lowerLeft.y ) {
			closestY = r.lowerLeft.y;
		} else if ( c.center.y > (r.lowerLeft.y + r.height) ) {
			closestY = r.lowerLeft.y + r.height;
		}

		return c.center.distSquared( closestX , closestY ) < (c.radius * c.radius);
	}
	
	/**
	 * HitBox에 대응하는 충돌 체크 메서드 입니다.
	 * @param c
	 * @param h
	 * @return
	 */
	public static boolean overlapCircleHitBox(final Circle c, final HitBox h) {

		float closestX = c.center.x;
		float closestY = c.center.y;

		if ( c.center.x < h.lowerLeft.x ) {
			closestX = h.lowerLeft.x;
		} else if ( c.center.x > (h.lowerLeft.x + h.width) ) {
			closestX = h.lowerLeft.x + h.width;
		}

		if ( c.center.y < h.lowerLeft.y ) {
			closestY = h.lowerLeft.y;
		} else if ( c.center.y > (h.lowerLeft.y + h.height) ) {
			closestY = h.lowerLeft.y + h.height;
		}

		return c.center.distSquared( closestX , closestY ) < (c.radius * c.radius);
	}
	
	/**
	 * 두 구의 충돌 여부를 확인
	 * 
	 * @param s1
	 * @param s2
	 * @return 충돌시 true, 아닐시 false
	 */
	public static boolean overlapSpheres(final Sphere s1, final Sphere s2) {

		final float distance = s1.center.distSquared( s2.center );
		final float radiusSum = s1.radius + s2.radius;
		return distance <= (radiusSum * radiusSum);
	}

	/**
	 * 구가 v3 가 정의하는 좌표에 포함되는지 체크 하는 메서드
	 * 
	 * @param c
	 * @param p
	 * @return 좌표에 구가 포함된다면 true , 아니라면 false
	 */
	public static boolean pointInSphere(final Sphere c, final V3 p) {

		return c.center.distSquared( p ) < (c.radius * c.radius);
	}
	/**
	 * 구가 X,Y,Z 가 정의하는 좌표에 포함되는지 체크 하는 메서드
	 * 
	 * @param c
	 * @param x
	 * @param y
	 * @param z
	 * @return 좌표에 구가 포함된다면 true , 아니라면 false
	 */
	public static boolean pointInSphere(final Sphere c, final float x, final float y, final float z) {

		return c.center.distSquared( x , y , z ) < (c.radius * c.radius);
	}
	/**
	 * 원이 v2 가 정의하는 좌표에 속하는지 체크하는 메서드
	 * 
	 * @param c
	 * @param p
	 * @return 원이 좌표속에 속한다면 true, 아니라면 false
	 */
	public static boolean pointInCircle(final Circle c, final V2 p) {

		return c.center.distSquared( p ) < (c.radius * c.radius);
	}
	/**
	 * 원이 X,Y 가 정의하는 좌표에 속하는지 체크하는 메서드
	 * 
	 * @param c
	 * @param x
	 * @param y
	 * @return 원이 좌표속에 속한다면 true, 아니라면 false
	 */
	public static boolean pointInCircle(final Circle c, final float x, final float y) {

		return c.center.distSquared( x , y ) < (c.radius * c.radius);
	}
	/**
	 * 사각형이 v2 가 정의하는 좌표에 속하는지 체크하는 메서드
	 * 
	 * @param r
	 * @param p
	 * @return 사각형이 좌표속에 속한다면 true, 아니라면 false
	 */
	public static boolean pointInRectangle(final Rectangle r, final V2 p) {

		return (r.lowerLeft.x <= p.x) && ((r.lowerLeft.x + r.width) >= p.x)
				&& (r.lowerLeft.y <= p.y)
				&& ((r.lowerLeft.y + r.height) >= p.y);
	}
	
	public static boolean pointInHitBox(final HitBox h, final V2 p) {

		return (h.lowerLeft.x <= p.x) && ((h.lowerLeft.x + h.width) >= p.x)
				&& (h.lowerLeft.y <= p.y)
				&& ((h.lowerLeft.y + h.height) >= p.y);
	}
	
	/**
	 * 사각형이 X,Y 가 정의하는 좌표에 속하는지 체크하는 메서드
	 * 
	 * @param r
	 * @param x
	 * @param y
	 * @return 사각형이 좌표속에 속한다면 true, 아니라면 false
	 */
	public static boolean pointInRectangle(final Rectangle r, final float x, final float y) {

		return (r.lowerLeft.x <= x) && ((r.lowerLeft.x + r.width) >= x)
				&& (r.lowerLeft.y <= y) && ((r.lowerLeft.y + r.height) >= y);
	}

	public static boolean pointInHitBox(final HitBox h, final float x, final float y) {

		return (h.lowerLeft.x <= x) && ((h.lowerLeft.x + h.width) >= x)
				&& (h.lowerLeft.y <= y) && ((h.lowerLeft.y + h.height) >= y);
	}
	
	/**
	 * 한 점이 BOX 속에 들어가 있는지의 여부를 판단
	 * @param point
	 * @param box
	 * @return 포함되어 있다면 true , 아니라면 false
	 */
	static public boolean pointInBox(V3 point , Box box){
		if ( point.x > box.LeftEdgeUp.x) return false;
		if ( point.x < box.ReightEdgeDown.x) return false;
		if ( point.y > box.LeftEdgeUp.y) return false;
		if ( point.y > box.ReightEdgeDown.y) return false;
		if ( point.z > box.LeftEdgeUp.z) return false;
		if ( point.z > box.ReightEdgeDown.z) return false;
		return true;
	}

	/**
	 * 두개의 라인이 겹치는지의 여부를 검사 합니다.
	 * @param line1
	 * @param line2
	 * @return
	 */
	static public boolean isIntersectLine(Line line1 , Line line2){
		double t, s, under , _t , _s;
		under = ((line2.end.y - line2.start.y) * (line1.end.x - line1.start.x)) - ((line2.end.x - line2.start.x) * (line1.end.y - line1.start.y));

		if(under == 0) return false;

		_t = ((line2.end.x - line2.start.x) * (line1.start.y - line2.start.y)) - ((line2.end.y - line2.start.y) * (line1.start.x - line2.start.x));
		_s = ((line1.end.x - line1.start.x) * (line1.start.y - line2.start.y)) - ((line1.end.y - line1.start.y) * (line1.start.x - line2.start.x));
		t = _t/under;
		s= _s/under;

		if((t < 0.0) || (t > 1.0) || (s < 0.0) || (s > 1.0)) return false;

		if((_t==0) && (_s==0)) return false; 

		return true;
	}
	/**
	 * 교차점을 구합니다.
	 * 
	 * @param line1
	 * @param line2
	 * @return 겹치지 않는다면 -1, -1 을 리턴 합니다.
	 * @return 겹친다면 겹치는 위치를 리턴 합니다.
	 */
	static public V2 getIntersectPoint(Line line1 , Line line2){
		final V2 tmp  = new V2(-1,-1);
		double t, s, under , _t , _s;
		under = ((line2.end.y - line2.start.y) * (line1.end.x - line1.start.x)) - ((line2.end.x - line2.start.x) * (line1.end.y - line1.start.y));
		if(under == 0) return tmp;
		_t = ((line2.end.x - line2.start.x) * (line1.start.y - line2.start.y)) - ((line2.end.y - line2.start.y) * (line1.start.x - line2.start.x));
		_s = ((line1.end.x - line1.start.x) * (line1.start.y - line2.start.y)) - ((line1.end.y - line1.start.y) * (line1.start.x - line2.start.x));
		t = _t/under;
		s= _s/under;
		if((t < 0.0) || (t > 1.0) || (s < 0.0) || (s > 1.0)) return tmp;
		if((_t==0) && (_s==0)) return tmp; 
		return tmp.set( (float)(line1.start.x + (t * (line1.end.x - line1.start.x))) , (float)(line1.start.y + (t * (line1.end.y - line1.start.y))) );
	}

}