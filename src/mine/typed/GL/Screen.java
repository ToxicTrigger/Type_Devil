package mine.typed.GL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.microedition.khronos.opengles.GL10;

import mine.tools.debug.TypeLogger;
import mine.typed.GL.renderer.Renderer;
import mine.typed.core.game.Entity;
import mine.typed.core.game.Message;
import mine.typed.core.interfaces.Game;

public abstract class Screen implements Entity{
    protected final Game game;
    
    public ArrayList<Renderer> renderers;
    private int rendererMinPriority;
    private Renderer[] renders;
    
    public ArrayList<Message> messages;
    private float tick;
    protected float time;
    public ArrayList<Entity> entitys;

    public Screen(final Game game,int RendererMinPriority) {
	this.game = game;
	renderers = new ArrayList<Renderer>();
	this.rendererMinPriority = RendererMinPriority;
	messages = new ArrayList<Message>();
	entitys = new ArrayList<Entity>();
	entitys.add(this);
	tick = 0;
    }
    
    public void addRenderer(Renderer render){
    	renderers.add(render);
    	
    	Renderer pri = null;
    	renders = new Renderer[this.renderers.size()];
    	
    	int len = renderers.size();
    	for(int i = 0; i < len; i++){
    		renders[i] = renderers.get(i);
    	}
    	
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
    }

    protected void callRenderers(float delta, GL10 gl){
	try{
	    	for(int i = renders.length - 1; i >= 0; i--){
	    	    try{
	    		if(renders[i].Priority >= this.rendererMinPriority) renders[i].draw(delta, gl);
	    	    }catch(NullPointerException e){
	    		e.printStackTrace();
	    		TypeLogger.getInstance().printMsg(this, this.getClass().getCanonicalName(), "renderer[" + i + "] is null");
	    	    }
	    		
	    	}
	}catch(NullPointerException ee){
	    ee.printStackTrace();
	    TypeLogger.getInstance().printMsg(this, this.getClass().getCanonicalName(), "No added Render. use <addRenderer(Renderer)>");
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
    
    public float getMessageTick(){ return tick; }
    public void setMessageTick(float del){ tick = del; }
    
    public void addMessage(Message msg){
	this.messages.add(msg);
    }
    public void addEntity(Entity ... ents){
	for(Entity ent : ents){
	    this.entitys.add(ent);
	}
    }
    public boolean removeEntity(Entity ent){
	boolean done = false;
	try{
	    done = entitys.remove(ent);
	}catch(Exception e){
	    e.printStackTrace();
	}
	return done;
    }
    
    public void getMessage(){
	//this.onHandle();
	try{
		for(Entity ent : entitys){
		    ent.onHandle();
		}
	}catch(ConcurrentModificationException e){
	    e.printStackTrace();
	}finally{
		for(int i = 0; i < messages.size(); i++){
		    messages.remove(i);
		}
	}
    }

}
