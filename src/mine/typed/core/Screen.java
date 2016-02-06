package mine.typed.core;

import java.util.ArrayList;

import mine.typed.GL.renderer.Renderer;
import mine.typed.core.interfaces.Game;

public abstract class Screen {
    protected final Game game;
    
    public ArrayList<Renderer> renderers;
    private int rendererMinPriority;

    public Screen(final Game game,int RendererMinPriority) {
	this.game = game;
	renderers = new ArrayList<Renderer>();
	this.rendererMinPriority = RendererMinPriority;
    }
    

    public void callRenderers(float delta){
    	
    	Renderer pri = null;
    	Renderer[] renders = new Renderer[this.renderers.size()];
    	
    	//순차 정렬
    	for(int k = 0; k < renders.length; k++){
    		for(int l = k + 1; l < renders.length; l++){
    			if(renders[k].Priority < renders[l].Priority){
    				pri = renders[k];
    				renders[k] = renders[l];
    				renders[l] = pri;
    			}
    		}
    	}
    	
    	for(int i = renders.length - 1; i >= 0; i--){
    		if(renders[i].Priority >= this.rendererMinPriority) renders[i].draw(delta);
    	}
    	
    }
    
    public void setRendererMinPriority(int priority){
    	rendererMinPriority = priority;
    }
    
    public int getRendererMinPriority(){
    	return this.rendererMinPriority;
    }
    
    public void updateFinally(float delta){
    	this.updateTouch();
    	this.update(delta);
    }

    /**
     * 주기적으로 업데이트 될 것 들을 적습니다.
     * 
     * @param deltaTime
     */
    public abstract void update(float deltaTime);

    public abstract void lateUpdate(float deltaTime);

    public abstract void resent(float deltaTime);


    /**
     * 일시정지 이벤트가 발생할 경우 처리할 내용을 기술합니다.
     */
    public abstract void pause();

    /**
     * 스크린 복귀시 할 행동
     */
    public abstract void resume();

    /**
     * 스크린이 제거 될때 행동
     */
    public abstract void dispose();

    /**
     * {@code update()} 에서 호출하세요
     */
    public abstract void updateTouch();

}
