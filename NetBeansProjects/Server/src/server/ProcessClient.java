
package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ProcessClient implements Runnable {
    Socket incoming;
    InputStream is;
    OutputStream os;
    PrintWriter out;
    Scanner in;
    String name;
    static ArrayList<ProcessClient> pss;
    NamesContainer NC;
    ProcessClient(Socket incoming,ArrayList<ProcessClient> pss){
        ProcessClient.pss = pss;
        this.incoming = incoming;
    }

    @Override
    public void run() {
        String message;        
        try {
            is = incoming.getInputStream();
            os = incoming.getOutputStream();
            in = new Scanner(is);
            out = new PrintWriter(os, true);
            boolean done = false;
            
            while (!done && in.hasNextLine())
            {  
               String line = in.nextLine();
               
               int index = line.indexOf("::");
               String command = line.substring(0,index);
               String closeHelper = line.substring(index+2);
               System.out.println(line);
               if (command.equals("name")){
                   this.addName(line, index);                   
                   continue;
               }               
               if (command.equalsIgnoreCase("close")){
                   this.closeConnections(closeHelper);                        
                   break;
               }
               if (command.equals("message")){
                   message = line.substring(index + 1);  
                   boolean checker = true;
                   for (int i = 0; i < NamesContainer.names.size(); i++){
                    if (line.contains(NamesContainer.names.get(i)+":")){
                        checker = false;
                        sendPrivateMessage SPM = new sendPrivateMessage();     
                        String nickname = NamesContainer.names.get(i);
                        int indx = line.indexOf(NamesContainer.names.get(i));
                        String privateMessage = line.substring(indx);
                        SPM.sendUserMessage(nickname, privateMessage, incoming);                         
                    }
                    else{
                        
                    }
                   }
                   if (checker){
                       for (int j = 0; j < pss.size(); j++){       
                            pss.get(j).sendMessage(name + " " + message);
                       }
                   }
               }
                  
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ProcessClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void sendMessage(String message) {
        out.println(message);
    }
    public void addName(String line, int index) {
        NamesContainer.names.add(line.substring(index + 2).trim());
        NamesContainer.users.put(line.substring(index + 2).trim(), incoming);
        NamesContainer.connectMap.put(incoming, line.substring(index + 2).trim());
        GUI gui = new GUI();
        gui.listRefresher();
        NC = new NamesContainer(incoming);
        Thread n = new Thread(NC);
        n.start();
        name = line.substring(index + 2);
    }
    public void closeConnections(String closeHelper){
        System.out.println("Клиент " + incoming.getInetAddress() + " отключается");                        
        NamesContainer.names.remove(closeHelper);
        NamesContainer.names.trimToSize();
        NamesContainer.users.remove(closeHelper);
        NamesContainer.connectMap.remove(incoming);
        for (int i = 0; i < NamesContainer.connections.size(); i++){
            this.incoming = NamesContainer.connections.get(i);
            try {
                os = incoming.getOutputStream();
                out = new PrintWriter (os, true);
                out.println("refresh::" + closeHelper);
            } 
            catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }                       
            
        }
    }
    
}
