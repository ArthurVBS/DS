package UDPExerciseOne;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientExerciseOne {

    public static int Sum(int a, int b){
        return a + b;
    }

    public static int Multiply(int a, int b){
        return a * b;
    }

    public static int Divide(int a, int b){
        int result = -1;

        try {
            result = a / b;
        } catch (ArithmeticException e){
            System.out.println ("Can't be divided by Zero " + e);
        }

        return result;
    }

    public static void main(String[] args) {
        DatagramSocket aSocket = null;

        try{
            aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName(args[0]);

            String action = args[1];

            int firstNumber = Integer.parseInt(args[2]);
            int secondNumber = Integer.parseInt(args[3]);

            int toReturn = switch (action) {
                case "Sum" -> Sum(firstNumber, secondNumber);
                case "Multiply" -> Multiply(firstNumber, secondNumber);
                case "Divide" -> Divide(firstNumber, secondNumber);
                default -> -1;
            };

            byte[] m = Integer.toString(toReturn).getBytes();

            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[13];

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (aSocket != null){
                aSocket.close();
            }
        }
    }
}
