package graf;
import java.sql.*;
import java.util.ArrayList;
class people{
    int ID;
    String phone;
    String name;
    String adress;
    
}
public class peopleTable {
    Connection connection;
    peopleTable(){
        connectDB conn = new connectDB();
        connection = conn.getConnection();
        this.install();
    }
    private void install(){
        try{
        Statement st = connection.createStatement();
        st.execute("create table if not exists people(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name text, adress text, phone text)");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void add(people p){
        try{
            Statement st = connection.createStatement(); 
            st.execute("insert into people (name, adress, phone)"
                    + " values('"+p.name+"','"+p.adress+"','"+p.phone+"')");
        }
        catch(SQLException e){
            
        }
    }
    
    public void update(people p) {
       try{
            Statement st = connection.createStatement(); 
            st.execute("update people set name = '"+p.name+"', adress = '"+p.adress+"', phone = '"+p.phone+"' where id = '" + String.valueOf(p.ID) + "'");
        }
        catch(SQLException e){
            
        }
    }
    public void clear(){
        try{
            Statement st= connection.createStatement();
            st.execute("drop table people");
        }
        catch(SQLException e){
            
        }
            
    }
    public void getData(ArrayList<String[]> c){
        try{
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery("select * from people");
            while(rs.next()){
                String[] tmp = new String[4];
                
                tmp[0] = rs.getString("ID");
                tmp[1] = rs.getString("name");
                tmp[2] = rs.getString("adress");
                tmp[3] = rs.getString("phone");
                
                c.add(tmp);
                
            }            
        }
        catch(SQLException e){
            
        }
    }
    public String[] getDataByID(int ID){
        String[] tmp = new String[4];
        try{
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery("select * from people where ID="+String.valueOf(ID));
            rs.next();
            
                tmp[0] = rs.getString("ID");
                tmp[1] = rs.getString("name");
                tmp[2] = rs.getString("adress");
                tmp[3] = rs.getString("phone");         
                       
        }
        catch(SQLException e){
            
        }
        return tmp;
    }
    
    public void delete(int ID) {
       try{
            Statement st = connection.createStatement(); 
            st.execute("DELETE FROM people where id = '" + String.valueOf(ID) + "'");
        }
        catch(SQLException e){
            
        }
    }
}
