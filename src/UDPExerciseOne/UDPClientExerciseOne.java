package UDPExerciseOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientExerciseOne {

    public static void main(String args[]) throws Exception
    {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        try{

            byte[] envio1 = new byte[10];
            byte[] envio2 = new byte[10];
            byte[] envio3 = new byte[10];

            String num1, num2, operacao;
            System.out.print("Operacao:\n1 - Soma\n2 - Multiplicacao\n3 - Divisao\n>>> ");
            operacao = entrada.readLine();
            System.out.println("[Apenas de 1-9] Valor 1:");
            System.out.print(">>> ");

            num1 = entrada.readLine();
            System.out.println("[Apenas de 1-9] valor 2:");
            System.out.print(">>> ");
            num2 = entrada.readLine();

            envio1 = num1.getBytes();
            envio2 = num2.getBytes();
            envio3 = operacao.getBytes();

            DatagramPacket envioPacket1 = new DatagramPacket(envio1,envio1.length,ip,9876);
            clientSocket.send(envioPacket1);
            DatagramPacket envioPacket2 = new DatagramPacket(envio2,envio2.length,ip,9876);
            clientSocket.send(envioPacket2);
            DatagramPacket envioPacket3 = new DatagramPacket(envio3,envio3.length,ip,9876);
            clientSocket.send(envioPacket3);

            byte[] buffer = new byte[13];
            DatagramPacket resultado = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(resultado);
            System.out.println("Resultado: "+new String(resultado.getData()));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if(clientSocket!=null){
                clientSocket.close();
            }
        }//fechando o try
    }//fechando a main
}//fechando a classe
