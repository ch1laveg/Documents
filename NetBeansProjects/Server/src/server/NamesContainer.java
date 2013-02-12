
package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NamesContainer implements Runnable {
    
    Socket incoming;
    InputStream is;
    OutputStream os;
    PrintWriter out;
    Scanner in;
    String name;
    ArrayList<ProcessClient> pss; 
    static Map<String, Socket> users = new ConcurrentHashMap<>();
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Socket> connections = new ArrayList<>();
    static Map<Socket, String> connectMap = new ConcurrentHashMap<>();
    NamesContainer(Socket incoming){
        connections.add(incoming);
    }
    

    
    @Override 
    public void run() {
        for (int j = 0; j < connections.size(); j++){
            try {
                this.incoming = connections.get(j);
                is = incoming.getInputStream();
                os = incoming.getOutputStream();
                in = new Scanner(is);
                out = new PrintWriter(os, true);
            
                for (int i = 0; i < NamesContainer.names.size(); i++){
                    out.println(names.get(i) + "::");
                    System.out.println("Передано " + names.get(i));
                }
            
            } catch (IOException ex) {
                Logger.getLogger(NamesContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    

    
}
