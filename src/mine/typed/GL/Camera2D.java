package mine.typed.GL;

import javax.microedition.khronos.opengles.GL10;

import mine.typed.core.V2;

/**
 * 드로잉된 객체를 화면에 보이게 해주는 클래스 이다.
 * 
 * @author mrminer
 *
 */
public class Camera2D {
    public final V2 position;
    public float zoom;
    public final float frustumWidth;
    public final float frustumHeight;
    final GLGraphics glGraphics;

    /**
     * 
     * @param glG
     * @param frustumWidth
     * @param frustumHeight
     */
    public Camera2D(final GLGraphics glG, final float frustumWidth,
	    final float frustumHeight) {

	this.glGraphics = glG;
	this.frustumWidth = frustumWidth;
	this.frustumHeight = frustumHeight;
	this.position = new V2(frustumWidth / 2, frustumHeight / 2);
	this.zoom = 1.0f;

    }

    /**
     * GL 관련 연산을 캡슐화 했다.
     */
    public void setViewportAndMatrices() {

	final GL10 gl = this.glGraphics.getGL();
	gl.glViewport(0, 0, this.glGraphics.getW(), this.glGraphics.getH());
	gl.glMatrixMode(GL10.GL_PROJECTION);
	gl.glLoadIdentity();
	gl.glOrthof(this.position.x - ((this.frustumWidth * this.zoom) / 2), this.position.x + ((this.frustumWidth * this.zoom) / 2), this.position.y - ((this.frustumHeight * this.zoom) / 2), this.position.y + ((this.frustumHeight * this.zoom) / 2), 1, -1);
	gl.glMatrixMode(GL10.GL_MODELVIEW);
	gl.glLoadIdentity();
    }

    /**
     * 화면상의 터치 좌표를 프로그램 속 월드 좌표계에 맞게 캐스팅 해준다.
     * 
     * @param touch
     */
    public void touchToWorld(final V2 touch) {

	touch.x = (touch.x / this.glGraphics.getW()) * this.frustumWidth * this.zoom;
	touch.y = (1 - (touch.y / this.glGraphics.getH())) * this.frustumHeight * this.zoom;
	touch.add(this.position)
		.sub((this.frustumWidth * this.zoom) / 2, (this.frustumHeight * this.zoom) / 2);
    }
}
