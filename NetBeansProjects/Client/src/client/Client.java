/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
/**
 *
 * @author ПК для начинающих
 */
public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
        
    }
   
}
