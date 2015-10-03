package toxic.mine.typed.neuron;

import mine.typed.core.V2;

public class GameObjectNeuron extends Neuron {
    public V2 pos;

    public GameObjectNeuron(float x, float y) {
	super();
	pos = new V2(x, y);
    }

    public GameObjectNeuron(float x, float y, Object data) {
	super(data);
	pos = new V2(x, y);
    }
}
