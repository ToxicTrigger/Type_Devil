package toxic.mine.typed.neuron;

public abstract class NeuronDocker
{

	public static void disconnect(Neuron out, Neuron in)
	{
		in.setState(NeuronState.Idle);
		in.updateState();
		out.setState(NeuronState.Idle);
		out.updateState();
	}

	public static boolean docking(Neuron out, Neuron in)
	{
		boolean isFinded = false;
		try
		{
			isFinded = findInCell(out, in);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		if( isFinded )
		{
			in.setState(NeuronState.Docked);
			in.updateState();
			out.setState(NeuronState.Docked);
			out.updateState();
		}
		if( !sendDataToOtherNeuronsTmpInData(out, in) ) return false;
		in.sendTmpDataToData();
		return true;
	}

	public static boolean isDocked(Neuron out, Neuron in)
	{
		if( !(out.hasDocked() & in.hasDocked()) ) return false;
		if( !out.getData().equals(in.getData()) ) return false;
		return true;
	}

	private static boolean findInCell(Neuron out, Neuron in)
	{
		if( in == null ) return false;
		if( in.getState() != NeuronState.Idle ) return false;
		in.setState(NeuronState.Finding);
		if( out == null ) return false;
		if( out.getState() != NeuronState.Idle ) return false;
		out.setState(NeuronState.Finding);

		if( in.getState() == NeuronState.Finding & out.getState() == NeuronState.Finding ) return true;
		return false;
	}

	public static boolean sendDataToOtherNeuronsTmpInData(Neuron out, Neuron in)
	{
		if( in.hasDocked() == false || out.hasDocked() == false ) return false;
		if( !(out.sendDataToTmpData() & in.getDataFromOutsTmpData(out)) ) return false;
		return true;
	}

}
