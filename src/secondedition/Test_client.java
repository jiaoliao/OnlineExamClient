/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondedition;


/**
 *
 * @author christine
 */
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Client send message to Server
 * @author christine
 */
class Client_send extends Thread {

    public void run(String content) {
        try {
            Socket s = new Socket("127.0.0.1", 2526);

            System.out.println("Connected with server for sending successfully!!");
            System.out.println("Data transfering...");
            
            OutputStream out = s.getOutputStream();
            String str = content;
            out.write(str.getBytes());
            System.out.println("Client Send:" + str);
            sleep((int) (100 * Math.random()));

            out.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}

/**
 * Client receive message from Server
 * @author christine
 */
class Client_Recv extends Thread {
    private String message;
    
    public void run() {
        byte buff[] = new byte[1024];
        try {
            Socket s = new Socket("127.0.0.1", 2527);
            
            System.out.println("Connected with server for receiving successfully!!");

            InputStream in = s.getInputStream();
            int n;
            n = in.read(buff);
            System.out.print("Received from server: ");
            message = new String(buff, 0, n);
            System.out.println(message);
            sleep((int) (100 * Math.random()));
            
            in.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
       
    public String getMsg() {
        return message;
    }
}

public class Test_client {

    private Client_Recv cr = new Client_Recv();
    private Client_send cs = new Client_send();

    public void start() {
        try {
            System.out.println("Client start");
            cs.start(); // Client_Receiver start
            cr.start(); // Client_Server start
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public Client_Recv getRecv() {
        return cr;
    }
    
    public Client_send getSend() {
        return cs;
    }
}
