package mine.typed.core;

/**
 * 스택을 구현한 클래스
 * 
 * @author mrminer
 */
public class Stack {

    /**
     * 객체를 저장할 배열
     */
    Object[] msize;
    /**
     * 현재 스택의 위치를 가리키는 커서
     */
    int mcuser;
    /**
     * 스택의 크기
     */
    int max;

    /**
     * 생성자.
     * 
     * @param StackSize
     *            스택의 크기
     */
    public Stack(final int StackSize) {

	this.msize = new Object[StackSize];
	this.mcuser = 0;
	this.max = StackSize;
    }

    /**
     * 스택에 삽입.
     * 
     * @param obj
     * @return 삽입에 성공했는지 여부
     */
    public boolean push(final Object obj) {

	if (this.max <= this.mcuser) {
	    return false;
	} else {
	    this.msize[this.mcuser] = obj;
	    this.mcuser++;
	    return true;
	}
    }

    /**
     * 스택에서 꺼내기
     * 
     * @return mcuser 값이 가리키는 주소에 존재하는 Object 를 반환
     */
    public Object pop() {

	if (this.mcuser <= -1) {
	    return null;
	} else {
	    this.mcuser--;
	    return this.msize[this.mcuser++];

	}
    }

    /**
     * key 값을 사용해 해당 배열이 공백인지의 여부를 확인한다.
     * 
     * @param key
     * @return key가 가리키는 배열이 공백이라면 true , 아니라면 false
     */
    public boolean isEmpty(final int key) {

	if (this.msize[key] == null) {
	    return true;
	} else {
	    return false;
	}
    }

}
