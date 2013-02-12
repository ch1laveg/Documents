package server;


import java.io.*;
import java.net.*;
import java.util.*;


public class inputConnect implements Runnable {
    static boolean endOfWhile = true;
    ArrayList<ProcessClient> pss;
    
    inputConnect (){
       pss = new ArrayList<ProcessClient>();
       
    }
    
    public void connect() {
        
        try {
            
            ServerSocket s = new ServerSocket(1991);
            while(true){
                System.out.println();
                Socket incoming = s.accept();
                ProcessClient ps = new ProcessClient(incoming,pss);
                pss.add(ps);
                Thread t = new Thread(ps);
                t.start();
                System.out.println("Клиент подключен " + incoming.getInetAddress());
            }   
        }
        catch(IOException e){
            
        }
        
        
    }
    
    @Override
    public void run() {
        this.connect();
    }

    
    
}
