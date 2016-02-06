package mine.typed.net.hell;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

import mine.typed.net.devil.Devil;

public class Hell {
    public static final int DEF_PORT = 4444;
    
    protected HashMap<String, Devil> clients;
    protected ServerSocket serSocket;
    
    protected int port;
    protected boolean hasGateClose;
    
    public Hell(){
	clients = new HashMap<String, Devil>();
	Collections.synchronizedMap(clients);
	port = DEF_PORT;
    }
    
    public Hell(int port){
	this();
	this.port = port;
    }
    
    public void openHell(){
	try{
	    hasGateClose = false;
	    System.out.println("<Hell>:" + "Gate Open..");

	    Socket sock;
	    
	    serSocket = new ServerSocket(port);
	    System.out.println("<Hell>:" + "Connect Void..");
	    
	    while(!hasGateClose){
		sock = serSocket.accept();
		HellReceiver recevier = new HellReceiver(sock, this);
		recevier.start();
	    }
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
}
