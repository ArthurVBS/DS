package UDPDefault;

import java.io.IOException;
import java.net.*;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;

        try{
            aSocket = new DatagramSocket();
            byte[] m = args[0].getBytes();

            System.out.println(m.length);
            InetAddress aHost = InetAddress.getByName(args[1]);

            System.out.println(aHost.getHostName());
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[13];

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            System.out.println("Reply: " + new String(reply.getData()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (aSocket != null){
                aSocket.close();
            }
        }
    }
}
