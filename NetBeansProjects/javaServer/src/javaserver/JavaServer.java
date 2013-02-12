
package javaserver;

import java.io.*;
import java.net.*;
import java.util.*;


public class JavaServer {

    
    public static void main(String[] args) {
        try
      {  
        
         ServerSocket s = new ServerSocket(8189);
         
         Socket incoming = s.accept( );

         try
         {
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();
            Scanner in = new Scanner(inStream);         
            PrintWriter out = new PrintWriter(outStream, true 
               );
            out.println( "Hello! Enter BYE to exit." );
            
            boolean done = false;
            while (!done && in.hasNextLine())
            {  
               double count = 0;
               String line = in.nextLine();            
               out.println("Echo: " + line);
               count = Double.parseDouble(line);
               out.println("Результат: " + count);
               
               if (line.trim().equals("BYE")){
                   System.out.println("Клинта отключается");
                   done = true;
               }
            }
             System.out.println("i'm here");
         }
         finally
         {
            incoming.close();
         }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
        
    }
}
