package UDPExerciseOne;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

public class UDPServerExerciseOne {
    /**
     * @param args
     */

    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] r1 = new byte[1];
        byte[] s1 = new byte[1];
        byte[] r2 = new byte[1];
        byte[] r3 = new byte[1];
        byte[] s2 = new byte[1];
        byte[] s3 = new byte[1];

        while(true)
        {
            //recebe informação do client
            DatagramPacket recebe1 = new DatagramPacket(r1, r1.length);
            serverSocket.receive(recebe1); //valor 1
            DatagramPacket recebe2 = new DatagramPacket(r2, r2.length);
            serverSocket.receive(recebe2);//valor 2
            DatagramPacket recebe3 = new DatagramPacket(r3, r3.length);
            serverSocket.receive(recebe3);//operacao

            //pega os dados
            String v1 = new String(recebe1.getData());
            String v2 = new String(recebe2.getData());
            String v3 = new String(recebe2.getData());

            //conversão
            int port = recebe1.getPort();
            int valor1 = Integer.parseInt(v1);
            int valor2 = Integer.parseInt(v2);

            int res = 0;

            if(v3.contains("1")) {
                //soma
                res = valor1 + valor2;
                String resultadoSoma = Integer.toString(res);
                s1 = resultadoSoma.getBytes();
            }

            if(v3.contains("2")) {
                //multiplicação
                res = valor1 * valor2;
                String resultadoMulti = Integer.toString(res);
                s1 = resultadoMulti.getBytes();
            }

            if(v3.contains("3")) {
                //divisão
                res = valor1 / valor2;
                String resultadoMulti = Integer.toString(res);
                s1 = resultadoMulti.getBytes();
            }

            InetAddress ip = recebe1.getAddress();
            DatagramPacket sendPacket = new DatagramPacket(s1,s1.length, ip, port);
            serverSocket.send(sendPacket);
            System.out.println("Resultado = " + res);

        }
    }//fechando a main
}//fechando a classe
