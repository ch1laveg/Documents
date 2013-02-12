
package requestreciever;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Refresher implements Runnable {
    DBWorker DBW;
    WorkFrame WF;
    public Refresher(){
        DBW = new DBWorker();
    }
    public Refresher(WorkFrame WF){
        this.WF = WF;
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    WF.listRefresher();
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    WF.listRefresher();
                }
            };
            try {
                Thread.currentThread().sleep(1000);                
                System.out.println("Обновлено " + i);
                i++;
            } catch (InterruptedException ex) {
                System.out.println("Не уснули");
            }
        }        
    }
    
}
