import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Rfc865UdpClient {
	public static void main(String[] argv) {
		//
		// 1. Open UDP socket
		//

		// cant use "static" as in the code template provided in manual
		// "static" can only be used outside of method since variables in methods dont
		// exist outside of method
		// cant be "final" since it is being reassigned
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
					InetAddress.getByName("localhost"), 17);
			socket.send(request);

			//
			// 3. Receive UDP reply from server
			//
			byte recvbuf[] = new byte[512];
			// receive from port 18
			DatagramPacket reply = new DatagramPacket(recvbuf, recvbuf.length,
					InetAddress.getByName("localhost"), 18);
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