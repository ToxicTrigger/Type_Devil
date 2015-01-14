package mine.typed.net.devil;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @see !!NOT DONE CODE!!
 * @author mrminer
 *
 */
public class Devil {

	public String 	mip;
	public int		mport;
	public Socket 	mSocket;
	public InputStream in;
	public DataInputStream dis;

	public Devil(final String ip , final int port ) {
		this.mip = ip;
		this.mport = port;
		this.init();
	}

	public void close(){
		try {
			this.dis.close( );
			this.mSocket.close( );
		} catch ( final IOException ee ) {
			ee.printStackTrace();
		}
		System.out.println( "Devil is gone." );
	}

	public void init(){

		try{
			this.mSocket = new Socket(this.mip , this.mport);

			//악마가 자신의 정보를 기록합니다.
			this.in = this.mSocket.getInputStream( );
			this.dis = new DataInputStream(this.in);
			System.out.println( "new Devil is Ready" );

		} catch ( final IOException e){
			e.printStackTrace( );
		}

	}

}
