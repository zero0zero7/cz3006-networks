/*
Name: Lim Xin Yi
Group: TCCA
IP Address: 172.21.146.128
*/



import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.UnsupportedEncodingException;

public class UDPclient_v2 {
	public static void main(String[] args) {
		//
		// 1. Open UDP socket
		//
        String serverAddr = args[0];
        int port = Integer.parseInt(args[1]);

		DatagramSocket socket = null;
		try {
			// listen on any port
			socket = new DatagramSocket();
            InetAddress IpAddress = InetAddress.getByName(serverAddr);
            // InetAddress IpAddress = InetAddress.getByName("SWLAB2-C.scse.ntu.edu.sg");
            socket.connect(IpAddress, port);
		} catch (SocketException e) {
			e.printStackTrace();
            System.exit(-1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
            System.exit(-1);
		}
		try {
			//
			// 2. Send UDP request to server
			//
			byte sendbuf[] = "Lim Xin Yi, TCCA, 172.21.146.128".getBytes("UTF-8");
			DatagramPacket request = new DatagramPacket(sendbuf, sendbuf.length);
			socket.send(request);

			//
			// 3. Receive UDP reply from server
			//
			byte recvbuf[] = new byte[512];
			DatagramPacket reply = new DatagramPacket(recvbuf, recvbuf.length);
			socket.receive(reply);

            String quote = new String(recvbuf);
            System.out.println(quote);

            socket.close();
		} catch (IOException e) {
		}
	}
}