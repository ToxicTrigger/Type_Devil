package mine.typed.net.devil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import mine.typed.core.game.Message;

/**
 * @see !!NOT DONE CODE!!
 * @author mrminer
 *
 */
public class Devil {

    public String mip;
    public int mport;
    public Socket mSocket;
    public InputStream in;
    public OutputStream out;
    public DataInputStream dis;
    public DataOutputStream dos;
    
    public Message inMsg, outMsg;

    public Devil(final String ip, final int port) {
	this.mip = ip;
	this.mport = port;

	this.init();
    }
    
    public void send(DataOutputStream dos, Message msg){
	
	try {
	    dos.writeUTF(msg.getJSONString());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }

    public void close() {
	try {
	    this.dis.close();
	    this.mSocket.close();
	} catch (final IOException ee) {
	    ee.printStackTrace();
	}
	System.out.println("Devil is gone.");
    }

    public void init() {

	try {
	    this.mSocket = new Socket(this.mip, this.mport);

	    // 악마가 자신의 정보를 기록합니다.
	    this.in = this.mSocket.getInputStream();
	    this.out = this.mSocket.getOutputStream();
	    this.dis = new DataInputStream(this.in);
	    this.dos = new DataOutputStream(this.out);
	    System.out.println("new Devil is Ready");
	    //send(new Message(""+this.mip, ""+this, "ctsM", "Hello?"));

	} catch (ConnectException e) {
	    System.out.println("server may not running");
	} catch (UnknownHostException e) {
	    System.out.println(this.mip + " is not Server IP!");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    String name;
    public void start() {
        try {
            mSocket = new Socket(mip, mport);
            System.out.println("서버와 연결되었습니다. 대화명을 입력하세요 : ");
            name = mSocket.getLocalAddress().getHostAddress()+":"+ mSocket.getLocalPort();
 
            ClientReceiver clientReceiver = new ClientReceiver(this);
            ClientSender clientSender = new ClientSender(mSocket);
             
            clientReceiver.start();
            clientSender.start();
        } catch (IOException e) {
        }
    }
 
    class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream input;
        Devil data;
 
        public ClientReceiver(Devil devil) {
            this.socket = devil.mSocket;
            data = devil;
            try {
                input = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
            }
        }
 
        @Override
        public void run() {
            while (input != null) {
                try{
                    data.inMsg = Message.getMessageFromJSON(input.readUTF());
                    System.out.println(input.readUTF());
                } catch (IOException e) {
                }
            }
        }
    }
 
    class ClientSender extends Thread {
        Socket socket;
        DataOutputStream output;
 
        public ClientSender(Socket socket) {
            this.socket = socket;
            try {
                output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF(name);
                System.out.println("대화방에 입장하였습니다.");
            } catch (Exception e) {
            }
        }
 
        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            String msg ="";
 
            while (output != null) {
//                try {
//                    msg = new Message(mip, this.socket.getLocalAddress().getHostAddress(), "msg", sc.nextLine()).getJSONString();
//                    if(msg.equals("exit"))
//                        System.exit(0);
//                     
//                    //output.writeUTF("[" + name + "] : " + msg);
//                    send(new Message(mip, this.socket.getLocalAddress().getHostAddress(), "msg", sc.nextLine()));
//                } catch (IOException e) {
//                }
        	outMsg = new Message(""+ mip + ":" + this.socket.getPort(), this.socket.getLocalAddress().getHostAddress() + ":" + this.socket.getLocalPort(), "msg", sc.nextLine());
        	send(output,outMsg);
            }
        }
    }

}
