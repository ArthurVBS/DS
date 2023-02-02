package UDPDefault;

import java.io.IOException;
import java.net.*;

public class UDPServer {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[13];

            while (true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println(request.getAddress());
                System.out.println(request.getPort());
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());

                aSocket.send(reply);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (aSocket != null){
                aSocket.close();
            }
        }
    }
}
