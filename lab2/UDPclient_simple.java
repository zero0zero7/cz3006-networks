import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.UnsupportedEncodingException;

public class UDPclient_v2 {
	public static void main(String[] argv) {
		//
		// 1. Open UDP socket
		//
        // String serverAddr = argv[0];
        // int port = Integer.parseInt(argv[1]);

		DatagramSocket socket = null;
		try {
			// listen on any port
			socket = new DatagramSocket(18);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {
			//
			// 2. Send UDP request to server
			//
			byte sendbuf[] = { 1, 2, 3, 4 };
			// send to port 17, where server is listening on
			DatagramPacket request = new DatagramPacket(sendbuf, sendbuf.length,
					InetAddress.getByName("SWLAB2-C.scse.ntu.edu.sg"), 17);
			socket.send(request);

			//
			// 3. Receive UDP reply from server
			//
			byte recvbuf[] = new byte[512];
			// receive from port 18
			DatagramPacket reply = new DatagramPacket(recvbuf, recvbuf.length);
			// receive() method blocks until a datagram is received.
			socket.receive(reply);
			// get the data from the packet and store in recvbuf
			recvbuf = reply.getData();
			System.out.println("Received: " + new String(recvbuf));
		} catch (IOException e) {
		}
		socket.close();
	}
}