
package javaclient;

import java.io.*;
import java.net.*;
import java.util.*;


public class JavaClient {

    public static void main(String[] args) {
        try
      {  

          Socket s = new Socket("localhost", 8189);
         try
         {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true );
            Scanner in2 = new Scanner(System.in);
            
            
            
            while (in.hasNextLine())
            {  
               String line = in.nextLine();
               System.out.println(line);
               System.out.println("next line");
               String command = in2.nextLine();
               out.println(command);
            }
            
            
         }
         finally
         {
            s.close();
         }
      }
      catch (IOException e)
      {  
      }
    }
}
