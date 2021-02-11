/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpechoclient;
import java.io.*;
import java.net.*;
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 

/**
 *
 * @author x17126991
 */
public class TCPCalcClient 
{ 
    public static void main(String[] args) throws IOException 
    { 
        InetAddress ip = InetAddress.getLocalHost(); 
        int port = 1998; 
        Scanner sc = new Scanner(System.in); 
  
        // Open socket. 
        Socket a = new Socket(ip, port); 
  
        //get the input and output stream 
        DataInputStream dis = new DataInputStream(a.getInputStream()); //input
        DataOutputStream dos = new DataOutputStream(a.getOutputStream()); //output
        
        while (true) 
        { 
            // Enter the equation in the form- 
            // "Number1 then the  operation you want to use and then Number2" 
            System.out.println("Please enter an equation in the format: Number Operation Number (allow one space between each input)"); 
            System.out.println("type Stop to terminate");
  
            String inp = sc.nextLine(); 
  
            if (inp.equals("Stop")) // terminates
                break; 
  
            // sends equation to server 
            dos.writeUTF(inp); 
  
            // wait till request is processed and sent back to client 
            String ans = dis.readUTF(); 
            System.out.println("Answer=" + ans); 
        } 
    } 
} 