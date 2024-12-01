import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Rfc865UdpServer {
   public static void main(String[] argv) {
      //
      // 1. Open UDP socket at well-known port
      //
      DatagramSocket socket = null;
      try {
         socket = new DatagramSocket(17);
      } catch (SocketException e) {
      }
      while (true) {
         try {
            //
            // 2. Listen for UDP request from client
            //
            byte recvbuf[] = new byte[512];
            DatagramPacket request = new DatagramPacket(recvbuf, recvbuf.length, InetAddress.getByName("localhost"),
                  17);
            socket.receive(request);

            //
            // 3. Send UDP reply to client
            //
            String msg = new String("REPLYING TO YOU");
            byte sendbuf[] = msg.getBytes();
            DatagramPacket reply = new DatagramPacket(sendbuf, sendbuf.length, InetAddress.getByName("localhost"), 18);
            socket.send(reply);
         } catch (IOException e) {
         }
         socket.close();
         break;
      }
   }
}