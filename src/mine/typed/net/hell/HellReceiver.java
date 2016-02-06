package mine.typed.net.hell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

import mine.typed.core.game.Entity;
import mine.typed.core.game.Message;
import mine.typed.net.devil.Devil;

public class HellReceiver extends Thread implements Entity{
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    
    Devil devil;
    
    private Hell hell;
    Message msg;
    
    public HellReceiver(Socket sock, Hell hell){
	this.socket = sock;
	this.hell = hell;
        try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                devil = new Devil(sock.getInetAddress().getHostAddress(), sock.getPort());
                devil.dis = this.input;
                devil.dos = this.output;
        } catch (IOException e) {
        }
    }
    
    @Override
    public void run() {
        String name = "";
        try {
            // 클라이언트가 서버에 접속하면 대화방에 알린다.
            name = input.readUTF();
            //sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + " are join Hell");

            hell.clients.put(name, devil);
            
            System.out.println(name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + " are join Hell");
            System.out.println("now " + hell.clients.size() + " Devils in Hell.");

            // 메세지 전송
            while (input != null) {
        	String data = input.readUTF();
        	msg = Message.getMessageFromJSON(data);
        	System.out.println(data);
                sendToAll(data);
                onHandle();
            }
        } catch (IOException e) {
        } finally {
            // 접속이 종료되면
            hell.clients.remove(name);
            //sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "님이 대화방에서 나갔습니다.");
            System.out.println(name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + " are Dead");
            System.out.println("now " + hell.clients.size() + " Devils alive");
        }
    }
    public void sendToAll(String message) {
	Iterator<String> it = hell.clients.keySet().iterator();
	while (it.hasNext()) {
	    Devil dev = null;
	    try {
		dev = hell.clients.get(it.next());
		dev.dos.writeUTF(message);
		}catch(Exception e){}finally{}
	    }
    }

    @Override
    public boolean onHandle() {

	
	if(msg.getData().equals("exit")){
		Iterator<String> it = hell.clients.keySet().iterator();
		while (it.hasNext()) {
		    Devil dev = null;

			dev = hell.clients.get(it.next());
			if(dev.inMsg.getSrc().equals(""+msg.getSrc())){
			    dev.close();
			    it.remove();
			
			}
		    }
	}
	else msg = null;
	return false;
    }
    
}
