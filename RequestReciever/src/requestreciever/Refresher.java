
package requestreciever;

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
        this.WF.listRefresher();
    }
    
}
