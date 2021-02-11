/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpechoserver;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author x17126991
 */
public class UDPDateTimeServer {

    private static final int PORT = 1998;
    private static DatagramSocket DatagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) {
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");  //format for which to display date
        LocalDateTime now = LocalDateTime.now();
        String date = (currentDate.format(now));
        DateTimeFormatter currentTime = DateTimeFormatter.ofPattern("HH:mm:ss");   //format for which to display time
        System.out.println(currentTime.format(now));
        String time = (currentTime.format(now));
        System.out.println("Opening port...\n");
        try {
            DatagramSocket = new DatagramSocket(PORT); //opening port
        } catch (SocketException e) {
            System.out.println("Unable to attach to port!"); // catch stating unable to connect
            System.exit(1);
        }
        run(date, time);
    }

    private static void run(String date, String time) {
        try {
            String messageIn, messageOut;
            int numMessages = 0;

            do {
                buffer = new byte[256]; 		
                inPacket = new DatagramPacket(buffer, buffer.length); 
                DatagramSocket.receive(inPacket);	

                InetAddress clientAddress = inPacket.getAddress();	
                int clientPort = inPacket.getPort();		

                messageIn = new String(inPacket.getData(), 0, inPacket.getLength());	

                System.out.println("Message received.");
                if (messageIn.equals("DATE")) {
                    messageOut = date;

                } else if (messageIn.equals("date")) {
                    messageOut = date;
                    
                    

                } else if (messageIn.equals("TIME")) {
                    messageOut = time;
                    
                } else if (messageIn.equals("time")) {
                    messageOut = time;
                    
                    

                } else {
                    messageOut = ("Message " + numMessages + ": " + messageIn);
                }
                outPacket = new DatagramPacket(messageOut.getBytes(),
                        messageOut.length(),
                        clientAddress,
                        clientPort);		
                DatagramSocket.send(outPacket);	
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {		//If exception thrown, close connection.
            System.out.println("\n Closing connection... ");
            DatagramSocket.close();				
        }
    }
}
