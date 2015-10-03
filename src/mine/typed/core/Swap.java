package mine.typed.core;

/**
 * 내부에서 객체를 스왑하기 편한 방법을 제공합니다.
 * 
 * @author mrminer
 *
 */
public class Swap {
    public Object x, y;

    /**
     * 값을 교환 합니다.
     * 
     * @param obj
     *            해당 객체의 x , y 를 교환 합니다.
     */
    public static void swap(Swap obj) {
	final Object temp = obj.x;
	obj.x = obj.y;
	obj.y = temp;
    }
}
