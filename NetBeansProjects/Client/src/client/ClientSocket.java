/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author ПК для начинающих
 */
public class ClientSocket implements Runnable {
    Socket socket;
    InputStream is;
    OutputStream os;
    PrintWriter out;
    Scanner in;
    GUI gui;
    int index;
    static DefaultListModel NamesList = new DefaultListModel();
    ClientSocket(GUI gui){
    this.gui = gui;
    }
    
    
    @Override
     public void run() {
        try {
            this.Connect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("ЗДЕСЬ ПРОИЗОШЛА КОВАРНАЯ ОШИБКА !!! !!!");
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //5.35.33.100
    public void Connect() throws UnknownHostException, IOException{
        socket = new Socket("5.35.33.100", 1991);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        out = new PrintWriter(os, true );
        in = new Scanner(is);
         while (in.hasNextLine()) { 
          
          String line = in.nextLine();
          if (line.contains("::")){   
          index = line.indexOf("::");
          String command = line.substring(0, index);
          String name = line.substring(index + 2);
          if (command.equals("refresh")){
              NamesList.remove(NamesList.indexOf(name));
              NamesList.trimToSize();
              this.gui.setText("Отключается  " + command);
              
          }
          else{this.namesCheck(command);
          NamesList.addElement(command);
          }
          System.out.println(NamesList);
          
           } 
          else    {this.gui.setText(line); }
         }
    }
    
    public void sendText(String message) {
        out.println(message);
    }
    

    
    public void nameClient () throws UnknownHostException, IOException {
        this.Connect();
        
    }
   public void namesCheck(String command) {
      String checker = command ;
       for (int i = 0 ; i < NamesList.size(); i++){
               if (NamesList.get(i).equals(checker)){
               NamesList.remove(i);
               
           }
       }
       
       NamesList.trimToSize();
       
   } 
   
}

     
     
    
    

