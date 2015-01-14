package toxic.mine.typed.neuron;

import mine.typed.core.ID;

public class Neuron {

	public final ID id;
	
	private NeuronState state;

	private Object tmpData;
	private Object Data;
	
	private boolean isDocked;
	private boolean isOverwriteData;
	
	public Neuron(){
		this.state = NeuronState.Idle;
		id = new ID(this);
	}
	public Neuron(Object data){
		this.Data = data;
		this.state = NeuronState.Idle;
		id = new ID(this);
	}
	
	boolean getDataFromOutsTmpData(Neuron out){
		this.tmpData = out.getTmpData();
		return true;
	}
	
	public Object getTmpData(){
		return this.tmpData;
	}
	
	public boolean isOverwriteData(){
		return this.isOverwriteData;
	}
	
	private boolean hasOverwriteData(){
		boolean over = false;
		over = isDuplicateDataAndTmpData();
		return over;
	}
	
	private boolean isDuplicateDataAndTmpData(){
		if(this.Data == null) return false;
		if(this.tmpData == null) return false;
		if(!this.Data.equals(tmpData)) return false;
		return true;
	}
	
	void sendTmpDataToData(){
		this.isOverwriteData = this.hasOverwriteData();
		this.Data = tmpData;
	}
	
	public Object getData(){
		return this.Data;
	}

	public boolean isEmptyDataCell(){
		if(this.Data == null) return true;
		return false;
	}
	
	public void updateState(){
		if(state == NeuronState.Docked) this.isDocked = true;
		if(state == NeuronState.Idle) this.isDocked = false;
		if(state == NeuronState.Idle) this.isOverwriteData = false;
	}
	
	public NeuronState getState(){
		return this.state;
	}
	void setState(NeuronState state){
		this.state = state;
	}

	
	public boolean hasDocked(){
		return this.isDocked;
	}
	boolean sendDataToTmpData(){
		Object tmpD = Data;
		tmpData = tmpD;
		return true;
	}
	
}
