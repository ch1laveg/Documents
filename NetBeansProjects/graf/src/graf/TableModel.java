
package graf;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel {
    peopleTable pt = new peopleTable();
    String[] columns;
    ArrayList<String[]> data;
    TableModel(){
        columns = new String[4];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Adress";
        columns[3] = "Phone";
        data = new <String[]>ArrayList();
        
        
    }

    @Override
    public int getRowCount() {
        return data.size();
        
    }

    @Override
    public int getColumnCount() {
        return 4;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
        
    }
    public String getColumnName(int index){
        return columns[index];
    }
 
    public void updateData(){
          data.clear();
          pt.getData(data);
    }
    public int getIDByRow(int columnIndex){
        return Integer.parseInt(data.get(columnIndex)[0]);
        
    }
    
}
