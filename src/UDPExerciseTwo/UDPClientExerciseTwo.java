package UDPExerciseTwo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class UDPClientExerciseTwo {

    public static void main(String[] args) throws Exception {
        try {
            int PORT = 8888;
            String HOST_NAME = "127.0.0.1";
            String clientMessage = "", serverMessage = "";

            Socket socket = new Socket(HOST_NAME, PORT);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (!clientMessage.equals("bye")) {
                System.out.println("Enter number :");
                clientMessage = br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);
            }

            outStream.close();
            outStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
