package UDPExerciseOne;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPServerExerciseOne {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[13];

            while (true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println("Received");

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());

                aSocket.send(reply);
                System.out.println("Sent");
                System.out.println("Reply: " + new String(reply.getData()));
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
