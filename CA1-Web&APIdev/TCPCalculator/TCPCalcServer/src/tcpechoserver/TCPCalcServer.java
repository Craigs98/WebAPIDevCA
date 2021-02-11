/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpechoserver;

import java.io.*;
import java.net.*;
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 

/**
 * @author x17126991
 */
public class TCPCalcServer 
{ 
    public static void main(String args[]) throws IOException 
    { 
  
        // Step 1: Establish the socket connection. 
        ServerSocket aa = new ServerSocket(1998); 
        Socket s = aa.accept(); 
        
  
        // Step 2: Processing the request. 
        DataInputStream dis = new DataInputStream(s.getInputStream());  // input
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());  // output
        
        while (true) 
        { 
            // wait for input 
            String input = dis.readUTF(); //Decodes utf into a string
  
            if(input.equals("Stop")) // termination of program
                break; 
  
            System.out.println("Equation received:-" + input); 
            int result; 
  
            // Use StringTokenizer to break the equation into operand and ( +, -, *, /)
            // operation 
            StringTokenizer st = new StringTokenizer(input); 
  
            int Number1 = Integer.parseInt(st.nextToken());  //input of 1st number
            String operation = st.nextToken(); 
            int Number2 = Integer.parseInt(st.nextToken());   //input of 2nd number
  
            // perform the required operation. 
            if (operation.equals("+")) 
            { 
                result = Number1 + Number2; 
            } 
  
            else if (operation.equals("-")) 
            { 
                result = Number1 - Number2; 
            } 
            else if (operation.equals("*")) 
            { 
                result = Number1 * Number2; 
            } 
            else
            { 
                result = Number1 / Number2; 
            } 
            System.out.println("Sending the result..."); 
  
            // send the result back to the client. 
            dos.writeUTF(Integer.toString(result)); 
        } 
    } 
} 