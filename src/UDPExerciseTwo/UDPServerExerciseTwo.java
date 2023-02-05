package UDPExerciseTwo;

import java.net.ServerSocket;
import java.net.Socket;

public class UDPServerExerciseTwo {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                UDPServerClientThreadExerciseTwo sct = new UDPServerClientThreadExerciseTwo(serverClient, counter); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}