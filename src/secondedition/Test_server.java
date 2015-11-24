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
import java.text.SimpleDateFormat;
import java.util.Date;
import jdk.nashorn.internal.parser.TokenType;

class Server_sen extends Thread {
    private GroupFile read;

    private String message = "";

    public void run() {
        try {
            ServerSocket svs = new ServerSocket(2527);
            
            Socket s = svs.accept(); //接收 client 連線

            System.out.println("Server connecting for sending successfully!!");
            System.out.println("Data transfering...");
            OutputStream out = s.getOutputStream();
            
            String str = getMsg();
            System.out.println("getMsg: " + str);

            out.write(str.getBytes());
            System.out.print("Server Send:" + str);
            sleep((int) (100 * Math.random()));
            out.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
    
    public String getMsg() {
        read = new GroupFile("", "program", ".txt");
        message = read.readFile();
        System.out.println(message);
        
        return message;
    }
}

class Server_Rec extends Thread {
    private GroupFile save;
    
    private String message = "";

    public void run() {
        byte buff[] = new byte[1024];
        try {
            ServerSocket svs = new ServerSocket(2526);

            Socket s = svs.accept();
            System.out.println("Server connecting for receiving successfully!!");

            InputStream in = s.getInputStream();
            int n;
            n = in.read(buff);
            message = new String(buff, 0, n);
            System.out.println("Received from client: " + message);
            sleep((int) (100 * Math.random()));
            
            in.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
//    Christine start
    public void saveMsg(String fileName) {
        save = new GroupFile("Ivy", fileName, ".java");
//        save.createFolder();
        save.createNewFile();
        try {
            save.writeToFile(message);
        }
        catch(IOException e) {
            System.out.println( e.getMessage() );
        }
        System.out.println("Data is saved successfully!\n\n");
    }
//    Christine end
    
    public String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy_MM_dd hh_mm_ss");
        Date date = new Date();
        String strDate = sdFormat.format(date);
        //System.out.println(strDate);
        return strDate;
    }
    
    public String getMessage() {
        return message;
    }
}

public class Test_server {
    private Server_sen ss = new Server_sen();
    private Server_Rec sr = new Server_Rec();
    private int flag_save = 0;

    public Server_Rec getRec() {
        return sr;
    }
    
    public Server_sen getSen() {
        return ss;
    }
    
    public void start() {
        try {
            System.out.println("Server start");
            InetAddress adr = InetAddress.getLocalHost(); //取得本機網址資訊
            ss.start();
            sr.start();
            
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
